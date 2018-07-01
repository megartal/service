package diringo.services.services;

import diringo.services.documents.City;
import diringo.services.repositories.city.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Akbar
 * @DATE 5/26/2018.
 */
@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public List<City> findCity(String city) {
        return cityRepository.findCity(city);
    }
}
