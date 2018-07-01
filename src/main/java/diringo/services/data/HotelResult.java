package diringo.services.data;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Akbar
 * @DATE 7/2/2018.
 */
@Getter
@Setter
public class HotelResult {
    private Set<OTAResult> otaResults = new HashSet<>();
}
