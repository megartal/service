package diringo.services.data;

import diringo.services.documents.OTAEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Akbar
 * @DATE 7/2/2018.
 */
@Getter
@Setter
@AllArgsConstructor
public class OTAResult {
    private OTAEntity ota;
    private int value;
    private String redirect;
}
