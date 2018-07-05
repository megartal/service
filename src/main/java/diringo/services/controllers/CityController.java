package diringo.services.controllers;

import diringo.services.documents.City;
import diringo.services.services.CityService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Akbar
 * @DATE 7/1/2018.
 */
@RestController
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("api/city")
    public List<City> city(@RequestParam(required = true) String city){
        return cityService.findCity(city);
    }
}
