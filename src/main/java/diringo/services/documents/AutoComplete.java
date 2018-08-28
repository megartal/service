package diringo.services.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * @Author Akbar
 * @DATE 8/25/2018.
 */
@Document(collection = "auto")
@Getter
@Setter
public class AutoComplete {
    public static final String DOCUMENT = "auto";
    public static final String NAME = "name";
    private String id = UUID.randomUUID().toString();
    private String name;
    private String city;
    private String province;
    private String type;

    public AutoComplete(String name, String city, String province, String type) {
        this.name = name;
        this.city = city;
        this.province = province;
        this.type = type;
    }
}
