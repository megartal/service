package diringo.services.repositories.city;

import diringo.services.documents.City;

import java.util.List;

/**
 * @Author Akbar
 * @DATE 7/1/2018.
 */
public interface CityRepositoryCustom {
    List<City> findCity(String city);
}
