package diringo.services.documents;

import diringo.services.models.Amenity;
import diringo.services.models.Location;
import diringo.services.models.OTAData;
import diringo.services.models.ScrapInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;
import java.util.*;
import java.util.List;

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
    private Integer stars;
    private String description;
    private Set<Amenity> amenities = new HashSet<>();
    private Location location = new Location("", "");
    private String mealPlan;
    private String cancelPolicy;
    private String accomType;
    private Set<OTAData> data = new HashSet<>();
}
