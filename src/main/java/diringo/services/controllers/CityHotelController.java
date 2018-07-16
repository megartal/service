package diringo.services.controllers;

import diringo.services.data.AutoComplete;
import diringo.services.services.CityService;
import diringo.services.services.HotelService;
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
    private final CityService cityService;
    private final HotelService hotelService;

    public CityHotelController(CityService cityService, HotelService hotelService) {
        this.cityService = cityService;
        this.hotelService = hotelService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("api/city")
    public List<AutoComplete> getAutoCompleteData(String term) {
        if (term == null)
            return new ArrayList<>();
        ArrayList<AutoComplete> list = cityService.findCity(term);
        if (list.size() > 5) {
            return list.subList(0, 5);
        } else {
            return list;
        }
    }
}
