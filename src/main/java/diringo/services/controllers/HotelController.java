package diringo.services.controllers;

import diringo.services.data.HotelRequest;
import diringo.services.data.HotelResult;
import diringo.services.services.HotelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/hotels")
    public List<HotelResult> getHotels(@RequestBody(required = true) HotelRequest request) {
        return hotelService.findHotels(request);
    }
}
