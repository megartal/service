package diringo.services.controllers;

import diringo.services.data.HotelRequest;
import diringo.services.services.HotelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/hotel")
    public void getHotels(@RequestBody(required = true) HotelRequest request){
        hotelService.findHotels(request);
    }
}
