package diringo.services.data;

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
    private RequestQuert query;
    private String mainImage;
    private String image1;
    private String image2;
    private String image3;
    //    private List<Image> images;
    private String address;
    private Integer stars;
    private String description;
    private String amenity1;
    private String amenity2;
    //    private Set<Amenity> amenities = new HashSet<>();
    private Location location = new Location("", "");
    //    private String mealPlan;
//    private String cancelPolicy;
//    private String accomType;
    private Set<OTAResult> otaResults = new HashSet<>();
    private int otaResultNum;
    private int hotelMinValue = Integer.MAX_VALUE;
}
