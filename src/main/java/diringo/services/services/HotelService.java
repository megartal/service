package diringo.services.services;

import diringo.services.GuestRoomMatch;
import diringo.services.data.HotelRequest;
import diringo.services.data.HotelResult;
import diringo.services.data.OTAResult;
import diringo.services.documents.Hotel;
import diringo.services.documents.OTAEntity;
import diringo.services.models.OTAData;
import diringo.services.models.Price;
import diringo.services.models.Room;
import diringo.services.repositories.hotel.HotelRepository;
import ir.huri.jcal.JalaliCalendar;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author Akbar
 * @DATE 5/17/2018.
 */
@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public void updateHotelPrices(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public void saveHotels(List<Hotel> hotels) {
        hotelRepository.saveAll(hotels);
    }

    public void saveHotel(Hotel hotelToPersist) {
        hotelRepository.save(hotelToPersist);
    }

    public List<HotelResult> findHotels(HotelRequest request) {
        String guestPriceKey = request.getGuest() + "" + request.getRooms();
        String[] fromArray = request.getFrom().split("/");
        String[] toArray = request.getTo().split("/");
        Date from = new JalaliCalendar(Integer.parseInt(fromArray[0]), Integer.parseInt(fromArray[1]), Integer.parseInt(fromArray[2])).toGregorian().getTime();
        Date to = new JalaliCalendar(Integer.parseInt(toArray[0]), Integer.parseInt(toArray[1]), Integer.parseInt(toArray[2])).toGregorian().getTime();
        List<Hotel> hotels = hotelRepository.findByCity(request.getCity());
        List<HotelResult> orderedHotels = new ArrayList<>();
        for (Hotel hotel : hotels) {
            Map<Integer, Integer> minRoomTypes = getRoomTypes();
            List<OTAResult> otaResults = new ArrayList<>();
            for (OTAData ota : hotel.getData()) {
                for (Room room : ota.getRooms()) {
                    if (room.getRoomType() == 0)
                        break;
                    int roomTotal = 0;
                    for (Price price : room.getPrices()) {
                        if (price.getDate().before(to) && price.getDate().after(from)) {
                            if (price.isAvailable()) {
                                roomTotal = price.getValue();
                            } else {
                                roomTotal = 0;
                                break;
                            }
                        }
                    }
                    if (roomTotal < minRoomTypes.get(room.getRoomType()) && roomTotal != 0)
                        minRoomTypes.put(room.getRoomType(), roomTotal);
                }
                int minPriceSuggestion = calculateMinPrice(guestPriceKey, minRoomTypes);
                //Fixme must add room ids and types to redirect URL
                if (minPriceSuggestion < Integer.MAX_VALUE)
                    otaResults.add(new OTAResult(new OTAEntity(ota.getOTAName()), minPriceSuggestion, ota.getRedirect()));
            }
            HotelResult hotelResult = new HotelResult();
            if (!otaResults.isEmpty()) {
                hotelResult.setHotelMinValue(otaResults.get(0).getValue());
            }
            Collections.sort(otaResults, (o1, o2) -> (o1.getValue() - o2.getValue()));
            hotelResult.setOtaResults(new HashSet<>(otaResults));
            hotelResult.setHotelId(hotel.getId());
            hotelResult.setHotelName(hotel.getName());
            hotelResult.setAddress(hotel.getAddress());
            hotelResult.setAccomType(hotel.getAccomType());
            hotelResult.setAmenities(hotel.getAmenities());
            hotelResult.setCancelPolicy(hotel.getCancelPolicy());
            hotelResult.setDescription(hotel.getDescription());
            hotelResult.setImages(hotel.getImages());
            hotelResult.setLocation(hotel.getLocation());
            hotelResult.setStars(hotel.getStars());
            hotelResult.setMealPlan(hotel.getMealPlan());
            orderedHotels.add(hotelResult);
        }
        Collections.sort(orderedHotels, (o1, o2) -> (o1.getHotelMinValue() - o2.getHotelMinValue()));
        if (request.getSort().equals("star_high")) {
            Collections.sort(orderedHotels, (o1, o2) -> (o2.getStars() - o1.getStars()));
        } else if (request.getSort().equals("star_low")) {
            Collections.sort(orderedHotels, (o1, o2) -> (o1.getStars() - o2.getStars()));
        }
        return orderedHotels;
    }

    private int calculateMinPrice(String key, Map<Integer, Integer> minRoomTypes) {
        Map<Integer, ArrayList<Integer>> result = new HashMap<>();
        ArrayList<ArrayList<Integer>> categories = GuestRoomMatch.match.get(key);
        int minPrice = Integer.MAX_VALUE;
        ArrayList<Integer> selectedCategory = null;
        for (ArrayList<Integer> category : categories) {
            int value = 0;
            for (Integer roomType : category) {
                value += minRoomTypes.get(roomType);
            }
            if (value < minPrice && value != 0) {
                minPrice = value;
                selectedCategory = category;
            }
        }
        return minPrice;
    }

    private Map<Integer, Integer> getRoomTypes() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, Integer.MAX_VALUE);
        map.put(2, Integer.MAX_VALUE);
        map.put(3, Integer.MAX_VALUE);
        map.put(4, Integer.MAX_VALUE);
        map.put(5, Integer.MAX_VALUE);
        return map;
    }
}
