package diringo.services.data;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Akbar
 * @DATE 7/1/2018.
 */
@Getter
@Setter
public class HotelRequest {
    private String city;
    private String from;
    private String to;
    private int guest;
    private int rooms;
    private String sort = "";
    private int page = 0;
    private String type = "hotel";
    private int star = 0;
    private int range = 0;
}
