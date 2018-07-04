package diringo.services.data;

import diringo.services.models.Amenity;
import diringo.services.models.Image;
import diringo.services.models.Location;
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
    private String HotelId;
    private String HotelName;
    private String mainImage;
    private Set<Image> images;
    private String address;
    private Integer stars;
    private String description;
    private Set<Amenity> amenities = new HashSet<>();
    private Location location = new Location("", "");
    private String mealPlan;
    private String cancelPolicy;
    private String accomType;
    private Set<OTAResult> otaResults = new HashSet<>();
    private int hotelMinValue;
}
