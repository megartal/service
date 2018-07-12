package diringo.services.documents;

import diringo.services.models.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
@Getter
@Setter
public class Hotel {
    private String id = UUID.randomUUID().toString();
    private String name;
    private Set<ScrapInfo> scrapInfo = new HashSet<>();
    private String district;
    private String city;
    private String mainImage;
    private List<Image> images = new ArrayList<>();
    private String address;
    private String category = "hotel";
    private String warning;
    private String information;
    private String rules;
    private Integer grade = 200;
    private Integer stars = 3;
    private String description;
    private List<Amenity> amenities = new ArrayList<>();
    private Location location = new Location("", "");
    private Set<OTAData> data = new HashSet<>();
}
