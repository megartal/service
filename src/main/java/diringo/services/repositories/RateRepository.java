package diringo.services.repositories;

import diringo.services.documents.Rate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Akbar
 * @DATE 5/24/2018.
 */
@Repository
public interface RateRepository extends MongoRepository<Rate, String> {
}
