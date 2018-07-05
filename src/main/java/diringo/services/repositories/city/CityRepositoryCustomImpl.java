package diringo.services.repositories.city;

import diringo.services.documents.City;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static diringo.services.documents.City.CITY;

/**
 * @Author Akbar
 * @DATE 7/1/2018.
 */
public class CityRepositoryCustomImpl implements CityRepositoryCustom {
    private final MongoTemplate template;

    public CityRepositoryCustomImpl(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public List<City> findCity(String city) {
        Query query = new Query();
        query.limit(2);
        query.addCriteria(Criteria.where(CITY).regex(city));
        return template.find(query, City.class);
    }
}
