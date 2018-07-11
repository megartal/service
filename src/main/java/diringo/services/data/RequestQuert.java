package diringo.services.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Akbar
 * @DATE 7/9/2018.
 */
@Getter
@Setter
@AllArgsConstructor
public class RequestQuert {
    private String city;
    private int night;
    private int guest;
    private int rooms;
    private String from;
    private String to;
    private String sort;
    private int page;

    public RequestQuert(int night, int guest, int rooms) {
        this.night = night;
        this.guest = guest;
        this.rooms = rooms;
    }
}
