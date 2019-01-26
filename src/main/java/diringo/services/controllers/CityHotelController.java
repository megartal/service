package diringo.services.controllers;

import diringo.services.documents.AutoComplete;
import diringo.services.services.AutoCompleteService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Akbar
 * @DATE 7/1/2018.
 */
@RestController
public class CityHotelController {
    private final AutoCompleteService autoCompleteService;

    public CityHotelController(AutoCompleteService autoCompleteService) {
        this.autoCompleteService = autoCompleteService;
    }

    @CrossOrigin(origins = {"https://www.jootrip.com", "http://localhost:3000"})
    @GetMapping("ppi/city")
    public List<AutoComplete> getAutoCompleteData(String term) {
        if (term == null)
            return new ArrayList<>();
        List<AutoComplete> search = autoCompleteService.findSearch(term);
        return search;
    }
}
