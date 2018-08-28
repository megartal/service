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
//        List<City> cities = cityRepository.findCity(term);
//        List<Hotel> hotels = hotelRepository.findHotel(term);
//        for (City city : cities) {
//            results.add(new AutoComplete(city.getCity(), city.getCity(), city.getDistrict(), "شهر"));
//        }
//        for (Hotel hotel : hotels) {
//            results.add(new AutoComplete(hotel.getName(), hotel.getCity(), hotel.getCity(), "هتل"));
//        }
        List<AutoComplete> autoCompletes = autoCompleteRepository.findTerm(term);
        return autoCompletes;
    }
}
