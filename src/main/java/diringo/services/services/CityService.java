package diringo.services.services;

import diringo.services.data.AutoComplete;
import diringo.services.documents.City;
import diringo.services.documents.Hotel;
import diringo.services.repositories.city.CityRepository;
import diringo.services.repositories.hotel.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Akbar
 * @DATE 5/26/2018.
 */
@Service
public class CityService {
    private final CityRepository cityRepository;
    private final HotelRepository hotelRepository;

    public CityService(CityRepository cityRepository, HotelRepository hotelRepository) {
        this.cityRepository = cityRepository;
        this.hotelRepository = hotelRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public ArrayList<AutoComplete> findCity(String term) {
        ArrayList<AutoComplete> results = new ArrayList();
        List<City> cities = cityRepository.findCity(term);
        List<Hotel> hotels = hotelRepository.findHotel(term);
        for (City city : cities) {
            results.add(new AutoComplete(city.getCity(), city.getCity(), city.getDistrict(), "شهر"));
        }
        for (Hotel hotel : hotels) {
            results.add(new AutoComplete(hotel.getName(), hotel.getCity(), hotel.getCity(), "هتل"));
        }
        return results;
    }
}
