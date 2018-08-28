package diringo.services.repositories.AutoComplete;

import diringo.services.documents.AutoComplete;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Akbar
 * @DATE 8/25/2018.
 */
@Repository
public interface AutoCompleteRepository extends MongoRepository<AutoComplete, String>, AutoCompleteRepositoryCustom {
}
