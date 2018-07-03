package diringo.services.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * @Author Akbar
 * @DATE 7/4/2018.
 */
@Getter
@Setter
@AllArgsConstructor
public class RoomPriceInfo {
    private int value;
    private ArrayList<String> rooms;
}
