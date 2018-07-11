package diringo.services.services;

import diringo.services.GuestRoomMatch;
import diringo.services.data.*;
import diringo.services.documents.Hotel;
import diringo.services.documents.OTAEntity;
import diringo.services.models.*;
import diringo.services.ota.OTA;
import diringo.services.repositories.OTARepository;
import diringo.services.repositories.hotel.HotelRepository;
import diringo.services.util.DataConverter;
import diringo.services.util.OTAFactory;
import ir.huri.jcal.JalaliCalendar;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author Akbar
 * @DATE 5/17/2018.
 */
@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final OTARepository otaRepository;

    public HotelService(HotelRepository hotelRepository, OTARepository otaRepository) {
        this.hotelRepository = hotelRepository;
        this.otaRepository = otaRepository;
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

    public Result findHotels(HotelRequest request) {
        Map<String, OTAEntity> otAs = getOTAs();
        String guestPriceKey = request.getGuest() + "" + request.getRooms();
        String[] fromArray = request.getFrom().split("/");
        String[] toArray = request.getTo().split("/");
        Date from = new JalaliCalendar(Integer.parseInt(fromArray[0]), Integer.parseInt(fromArray[1]), Integer.parseInt(fromArray[2])).toGregorian().getTime();
        Date rawDateTo = new JalaliCalendar(Integer.parseInt(toArray[0]), Integer.parseInt(toArray[1]), Integer.parseInt(toArray[2])).toGregorian().getTime();
        Date to = DateUtils.addHours(rawDateTo, 3);
        List<Hotel> hotels = hotelRepository.findByCity(request.getCity());
        List<HotelResult> orderedHotels = new ArrayList<>();
        for (Hotel hotel : hotels) {
            List<OTAResult> otaResults = new ArrayList<>();
            for (OTAData ota : hotel.getData()) {
                OTA otaInstance = OTAFactory.create(ota.getOTAName());
//                RedirectData redirectData = new RedirectData();
                Map<Integer, Integer> minRoomTypes = getRoomTypes();
                Map<Integer, String> minRoomNames = new HashMap<>();
                for (Room room : ota.getRooms()) {
//                    redirectData.setUrlPattern(ota.getRedirect());
//                    redirectData.getRoomIds().put(room.getRoomId());
//                    redirectData.getMeta().add(room.getMeta());
                    if (room.getRoomType() == 0)
                        break;
                    int roomTotal = 0;
                    for (Price price : room.getPrices()) {
                        if (price.getDate().before(to) && price.getDate().after(from)) {
                            if (price.isAvailable()) {
                                roomTotal += price.getValue();
                            } else {
                                roomTotal = 0;
                                break;
                            }
                        }
                    }
                    if (roomTotal < minRoomTypes.get(room.getRoomType()) && roomTotal > 0) {
                        minRoomTypes.put(room.getRoomType(), roomTotal);
                        minRoomNames.put(room.getRoomType(), room.getRoomName());
                    }
                }
                try {
                    RoomPriceInfo minPriceSuggestion = calculateMinPrice(guestPriceKey, minRoomTypes, minRoomNames);
                    if (minPriceSuggestion.getValue() < Integer.MAX_VALUE && minPriceSuggestion.getValue() > 0)
                        otaResults.add(new OTAResult(getOTAs().get(ota.getOTAName()), minPriceSuggestion, ota.getRedirect()));
                } catch (Exception e) {
                    System.out.println("");
                }
            }
            HotelResult hotelResult = new HotelResult();
            if (!otaResults.isEmpty()) {
                hotelResult.setHotelMinValue(otaResults.get(0).getPriceInfo().getValue());
            }
            Collections.sort(otaResults, (o1, o2) -> (o1.getPriceInfo().getValue() - o2.getPriceInfo().getValue()));
            hotelResult.setOtaResults(new HashSet<>(otaResults));
            hotelResult.setOtaResultNum(otaResults.size());
            hotelResult.setQuery(new RequestQuert((to.getDay() - from.getDay()),
                    request.getGuest(), request.getRooms()));
            hotelResult.setHotelId(hotel.getId());
            hotelResult.setHotelName(hotel.getName());
            hotelResult.setMainImage(hotel.getMainImage());
            if (hotel.getImages().size() > 2) {
                hotelResult.setImage1(hotel.getImages().get(0).getSrc());
                hotelResult.setImage2(hotel.getImages().get(1).getSrc());
                hotelResult.setImage3(hotel.getImages().get(2).getSrc());
            }
            for (Amenity amenity : hotel.getAmenities()) {
                if (amenity.getName().contains("اینترنت در اتاق"))
                    hotelResult.setInternet(true);
                if (amenity.getName().contains("پارکینگ"))
                    hotelResult.setParking(true);
            }
            hotelResult.setStars(hotel.getStars());
            hotelResult.setAddress(hotel.getAddress());
            hotelResult.setDesc(hotel.getDescription());
            hotelResult.setLocation(hotel.getLocation());
            hotelResult.setAmenities(hotel.getAmenities());
            orderedHotels.add(hotelResult);
        }
        Collections.sort(orderedHotels, (o1, o2) -> (o1.getHotelMinValue() - o2.getHotelMinValue()));
        if (request.getSort().equals("stars-desc")) {
            Collections.sort(orderedHotels, (o1, o2) -> (o2.getStars() - o1.getStars()));
        } else if (request.getSort().equals("stars-asc")) {
            Collections.sort(orderedHotels, (o1, o2) -> (o1.getStars() - o2.getStars()));
        }
        List<HotelResult> hotelResults = orderedHotels.subList(request.getPage() * 10, request.getPage() * 10 + 10);
        Result result = new Result(hotelResults, new RequestQuert(request.getCity(), (to.getDay() - from.getDay()), request.getGuest(),
                request.getRooms(), DataConverter.farsiDate(request.getFrom()), DataConverter.farsiDate(request.getTo()), DataConverter.sortConv(request.getSort()), request.getPage())
                , orderedHotels.size());
        return result;
    }

    private Map<String, OTAEntity> getOTAs() {
        Map<String, OTAEntity> map = new HashMap<>();
        List<OTAEntity> all = otaRepository.findAll();
        for (OTAEntity otaEntity : all) {
            map.put(otaEntity.getName(), otaEntity);
        }
        return map;
    }

    private RoomPriceInfo calculateMinPrice(String key, Map<Integer, Integer> minRoomTypes, Map<Integer, String> minRoomNames) throws Exception {
        Map<Integer, ArrayList<Integer>> result = new HashMap<>();
        ArrayList<ArrayList<Integer>> categories = GuestRoomMatch.match.get(key);
        int minPrice = Integer.MAX_VALUE;
        ArrayList<String> selectedCategory = null;
        for (ArrayList<Integer> category : categories) {
            ArrayList<String> roomsCategory = new ArrayList<>();
            int value = 0;
            for (Integer roomType : category) {
                value += minRoomTypes.get(roomType);
                roomsCategory.add(minRoomNames.get(roomType));
            }
            if (value < minPrice && value != 0) {
                minPrice = value;
                selectedCategory = roomsCategory;
            }
        }
        return new RoomPriceInfo(minPrice, selectedCategory);
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
