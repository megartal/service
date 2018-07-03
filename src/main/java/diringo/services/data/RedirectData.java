package diringo.services.data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Akbar
 * @DATE 7/4/2018.
 */
@Getter
@Setter
public class RedirectData {
    private Map<String, Integer> roomIds = new HashMap<>();
    private ArrayList<String> meta;
    private String urlPattern;
}
