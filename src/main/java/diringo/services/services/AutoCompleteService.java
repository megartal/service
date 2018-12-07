package diringo.services.services;

import diringo.services.documents.AutoComplete;
import diringo.services.repositories.AutoComplete.AutoCompleteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Akbar
 * @DATE 8/25/2018.
 */
@Service
public class AutoCompleteService {
    private final AutoCompleteRepository autoCompleteRepository;

    public AutoCompleteService(AutoCompleteRepository autoCompleteRepository) {
        this.autoCompleteRepository = autoCompleteRepository;
    }

    public List<AutoComplete> findSearch(String term) {
        ArrayList<AutoComplete> results = new ArrayList();
        List<AutoComplete> autoCompletes = autoCompleteRepository.findTop5ByNameStartsWith(term);
        if (autoCompletes.size() == 0)
            autoCompletes = autoCompleteRepository.findTerm(term);
        for (AutoComplete autoComplete : autoCompletes) {
            if (autoComplete.getProvince() == null) {
                autoComplete.setProvince(autoComplete.getCity());
                results.add(autoComplete);
            } else {
                results.add(autoComplete);
            }
        }
        return results;
    }
}
