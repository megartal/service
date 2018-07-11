package diringo.services.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author Akbar
 * @DATE 7/11/2018.
 */
@Getter
@Setter
@AllArgsConstructor
public class Result {
    private List<HotelResult> hotelResult;
    private RequestQuert query;
    private int resultNum;
}
