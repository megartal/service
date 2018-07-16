package diringo.services.repositories.hotel;

import diringo.services.documents.Hotel;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static diringo.services.documents.Hotel.NAME;


/**
 * @Author Akbar
 * @DATE 7/1/2018.
 */
public class HotelRepositoryCustomImpl implements HotelRepositoryCustom {
    private final MongoTemplate template;

    public HotelRepositoryCustomImpl(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public List<Hotel> findHotel(String term) {
        Query query = new Query();
        query.limit(5);
        query.addCriteria(Criteria.where(NAME).regex(term));
        return template.find(query, Hotel.class);
    }
}
