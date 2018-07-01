package diringo.services.data;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author Akbar
 * @DATE 7/1/2018.
 */
@Getter
@Setter
public class HotelRequest {
    private String city;
    private Date from;
    private Date to;
    private int guest;
    private int rooms;
}
