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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                            if (price.getDate().before(request.getTo()) && price.getDate().after(request.getFrom())) {
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
            orderedHotels.add(hotelResult);
        }
        return orderedHotels;
    }
}
