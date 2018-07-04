package diringo.services.services;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        String[] fromArray = request.getFrom().split("/");
        String[] toArray = request.getTo().split("/");
        Date from = new JalaliCalendar(Integer.parseInt(fromArray[0]), Integer.parseInt(fromArray[1]), Integer.parseInt(fromArray[2])).toGregorian().getTime();
        Date to = new JalaliCalendar(Integer.parseInt(toArray[0]), Integer.parseInt(toArray[1]), Integer.parseInt(toArray[2])).toGregorian().getTime();
        List<Hotel> hotels = hotelRepository.findByCity(request.getCity());
        List<HotelResult> orderedHotels = new ArrayList<>();
        for (Hotel hotel : hotels) {
            OTAResult otaResult = null;
            for (OTAData ota : hotel.getData()) {
                int OTAMinPrice = Integer.MAX_VALUE;
                for (Room room : ota.getRooms()) {
                    int roomTotal = 0;
                    for (Price price : room.getPrices()) {
                        if (!price.isAvailable()) {
                            break;
                        } else {
                            if (price.getDate().before(to) && price.getDate().after(from)) {
                                roomTotal = price.getValue();
                            }
                        }
                    }
                    if (roomTotal < OTAMinPrice && roomTotal != 0)
                        OTAMinPrice = roomTotal;
                }
                otaResult = new OTAResult(new OTAEntity(ota.getOTAName()), OTAMinPrice);
            }
            HotelResult hotelResult = new HotelResult();
            hotelResult.getOtaResults().add(otaResult);
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
            //sort
        }
        return orderedHotels;
    }
}
