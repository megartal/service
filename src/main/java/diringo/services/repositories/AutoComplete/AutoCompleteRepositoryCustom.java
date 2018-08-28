package diringo.services.repositories.AutoComplete;

import diringo.services.documents.AutoComplete;

import java.util.List;

/**
 * @Author Akbar
 * @DATE 8/25/2018.
 */
public interface AutoCompleteRepositoryCustom {
    List<AutoComplete> findTerm(String term);
}
