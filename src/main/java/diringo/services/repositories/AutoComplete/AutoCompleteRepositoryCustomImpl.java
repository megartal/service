package diringo.services.repositories.AutoComplete;

import diringo.services.documents.AutoComplete;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static diringo.services.documents.AutoComplete.NAME;

/**
 * @Author Akbar
 * @DATE 8/25/2018.
 */
public class AutoCompleteRepositoryCustomImpl implements AutoCompleteRepositoryCustom {
    private final MongoTemplate template;

    public AutoCompleteRepositoryCustomImpl(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public List<AutoComplete> findTerm(String term) {
        Query query = new Query();
        query.limit(5);
        query.addCriteria(Criteria.where(NAME).regex(term));
        return template.find(query, AutoComplete.class);

    }
}
