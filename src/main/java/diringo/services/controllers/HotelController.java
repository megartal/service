package diringo.services.controllers;

import diringo.services.data.HotelRequest;
import diringo.services.data.HotelResult;
import diringo.services.services.HotelService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Akbar
 * @DATE 7/1/2018.
 */
@RestController
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api/search")
    public List<HotelResult> getHotels(HotelRequest request) {
        return hotelService.findHotels(request);
    }
}
