package diringo.services.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * @author Akbar
 * @since 4/23/2018
 */
@Getter
@Setter
@Document(collection = "OTA")
public class OTAEntity {
    private String id = UUID.randomUUID().toString();
    private String name;
    private String website;
    private String logo;

    public OTAEntity(String otaName) {
        this.name = otaName;
    }
//    private String roomDivSelector;
//    private String webservice;
//    private String roomXpath;
//    private String priceXpath;

}
