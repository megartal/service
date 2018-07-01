package diringo.services.services;

import diringo.services.data.HotelRequest;
import diringo.services.documents.Hotel;
import diringo.services.models.OTAData;
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

    public void findHotels(HotelRequest request) {
        List<Hotel> hotels = hotelRepository.findByCity(request.getCity());
        List<Hotel> orderedHotels = new ArrayList<>();
        for (Hotel hotel : hotels) {
            for (OTAData otaData : hotel.getData()) {
                
            }

        }

    }
}
