package diringo.services.data;

import diringo.services.models.Amenity;
import diringo.services.models.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
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
    private Integer stars;
    private String address;
    private int grade;
    private String desc;
    private Location location;
    private List<Amenity> amenities;
    private boolean internet;
    private boolean parking;
    private Set<OTAResult> otaResults = new HashSet<>();
    private int otaResultNum;
    private int hotelMinValue = Integer.MAX_VALUE;
}
