package diringo.services.services;

import diringo.services.documents.City;
import diringo.services.repositories.AutoComplete.AutoCompleteRepository;
import diringo.services.repositories.city.CityRepository;
import diringo.services.repositories.hotel.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Akbar
 * @DATE 5/26/2018.
 */
@Service
public class CityService {
    private final CityRepository cityRepository;
    private final HotelRepository hotelRepository;

    public CityService(CityRepository cityRepository, HotelRepository hotelRepository, AutoCompleteRepository autoCompleteRepository) {
        this.cityRepository = cityRepository;
        this.hotelRepository = hotelRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City findCityByName(String city) {
        return cityRepository.findByCity(city).get();
    }
}
