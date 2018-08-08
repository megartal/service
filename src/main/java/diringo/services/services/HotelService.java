package diringo.services.services;

import diringo.services.GuestRoomMatch;
import diringo.services.data.*;
import diringo.services.documents.City;
import diringo.services.documents.Hotel;
import diringo.services.documents.OTAEntity;
import diringo.services.models.*;
import diringo.services.repositories.OTARepository;
import diringo.services.repositories.hotel.HotelRepository;
import diringo.services.util.DataConverter;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author Akbar
 * @DATE 5/17/2018.
 */
@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final OTARepository otaRepository;
    private final CityService cityService;

    public HotelService(HotelRepository hotelRepository, OTARepository otaRepository, CityService cityService) {
        this.hotelRepository = hotelRepository;
        this.otaRepository = otaRepository;
        this.cityService = cityService;
    }

    public Result findHotels(HotelRequest request) {
        String query = request.getQ().replaceAll("\\+", " ");
        HotelResult hotelQueryResult = null;
        Result result = null;
        try {
            String guestPriceKey = request.getGuest() + "" + request.getRooms();
            Date from = DataConverter.jalaliToJavaDate(request.getFrom());
            Date to = DataConverter.jalaliToJavaDate(request.getTo());
            to = DateUtils.addHours(to, 3);
            City city = cityService.findCityByName(request.getCity());
            List<Hotel> hotels = hotelRepository.findByCity(request.getCity());
            List<HotelResult> orderedHotels = new ArrayList<>();
            for (Hotel hotel : hotels) {
                if (hotel.getMainImage() == null || hotel.getImages().size() == 0)
                    continue;
                if (request.getStar() != 0 && hotel.getStars() != request.getStar())
                    continue;
                if (!hotel.getCategory().equals(request.getType()))
                    continue;
                List<OTAResult> otaResults = new ArrayList<>();
                for (OTAData ota : hotel.getData()) {
                    Map<Integer, Integer> minRoomTypes = getRoomTypes();
                    Map<Integer, String> minRoomNames = new HashMap<>();
                    for (Room room : ota.getRooms()) {
                        if (room.getRoomType() == 0 || room.getRoomType() > 9)
                            continue;
                        int roomTotal = 0;
                        for (Price price : room.getPrices()) {
                            if (price.getDate().before(to) && price.getDate().after(from)) {
                                if (price.isAvailable()) {
                                    roomTotal += price.getValue();
                                } else {
                                    roomTotal = 0;
                                    break;
                                }
                            }
                        }
                        if (roomTotal < minRoomTypes.get(room.getRoomType()) && roomTotal > 0) {
                            minRoomTypes.put(room.getRoomType(), roomTotal);
                            minRoomNames.put(room.getRoomType(), room.getRoomName());
                        }
                    }
                    try {
                        RoomPriceInfo minPriceSuggestion = calculateMinPrice(guestPriceKey, minRoomTypes, minRoomNames);
                        if (minPriceSuggestion.getValue() < Integer.MAX_VALUE && minPriceSuggestion.getValue() > 0) {
                            String redirectURL = ota.getRedirect() + getRedirectDate(ota.getOTAName(), request.getFrom(), request.getTo());
                            otaResults.add(new OTAResult(getOTAs().get(ota.getOTAName()), minPriceSuggestion, redirectURL));
                        }
                    } catch (Exception e) {
                        System.out.println("");
                    }
                }
                HotelResult hotelResult = new HotelResult();
                Collections.sort(otaResults, (o1, o2) -> (o1.getPriceInfo().getValue() - o2.getPriceInfo().getValue()));
                if (!otaResults.isEmpty()) {
                    hotelResult.setHotelMinValue(otaResults.get(0).getPriceInfo().getValue());
                }
                if (request.getRange() == 1) {
                    if (hotelResult.getHotelMinValue() > 200000)
                        continue;
                } else if (request.getRange() == 2) {
                    if (hotelResult.getHotelMinValue() > 400000 || hotelResult.getHotelMinValue() < 200000)
                        continue;
                } else if (request.getRange() == 3) {
                    if (hotelResult.getHotelMinValue() > 700000 || hotelResult.getHotelMinValue() < 400000)
                        continue;
                } else if (request.getRange() == 4) {
                    if (hotelResult.getHotelMinValue() > 1000000 || hotelResult.getHotelMinValue() < 700000)
                        continue;
                } else if (request.getRange() == 5) {
                    if (hotelResult.getHotelMinValue() < 1000000)
                        continue;
                }
                if (hotelResult.getHotelMinValue() == Integer.MAX_VALUE)
                    continue;
                hotelResult.setOtaResults(new ArrayList<>(otaResults));
                hotelResult.setOtaResultNum(otaResults.size());
                hotelResult.setQuery(new RequestQuery(TimeUnit.DAYS.convert((to.getTime() - from.getTime()), TimeUnit.MILLISECONDS),
                        request.getGuest(), request.getRooms()));
                hotelResult.setHotelId(hotel.getId());
                hotelResult.setHotelName(hotel.getName());
                String mainImage = hotel.getMainImage().replace("https://images.jabama.com/", "mainImage-");
                mainImage = mainImage + ".jpg";
                hotelResult.setMainImage(mainImage);
                if (hotel.getImages().size() > 2) {
                    hotelResult.setImage1(hotel.getImages().get(0).getSrc().replace("/", "-"));
                    hotelResult.setImage2(hotel.getImages().get(1).getSrc().replace("/", "-"));
                    hotelResult.setImage3(hotel.getImages().get(2).getSrc().replace("/", "-"));
                }
//                for (Amenity amenity : hotel.getAmenities()) {
//                    if (amenity.getName().contains("اینترنت در اتاق"))
//                        hotelResult.setInternet(true);
//                    if (amenity.getName().contains("پارکینگ"))
//                        hotelResult.setParking(true);
//                }
                hotelResult.setStars(hotel.getStars());
                hotelResult.setAddress(hotel.getAddress());
                hotelResult.setDesc(hotel.getDescription());
                hotelResult.setLocation(hotel.getLocation());
                hotelResult.setGrade(hotel.getGrade());
                if (query.equals(hotel.getName())) {
                    hotelQueryResult = hotelResult;
                } else {
                    orderedHotels.add(hotelResult);
                }
            }
            Collections.sort(orderedHotels, (o1, o2) -> (o1.getHotelMinValue() - o2.getHotelMinValue()));
            if (request.getSort().equals("stars-desc")) {
                Collections.sort(orderedHotels, (o1, o2) -> (o2.getStars() - o1.getStars()));
            } else if (request.getSort().equals("stars-asc")) {
                Collections.sort(orderedHotels, (o1, o2) -> (o1.getStars() - o2.getStars()));
            } else if (request.getSort().equals("popularity")) {
                Collections.sort(orderedHotels, (o1, o2) -> (o2.getGrade() - o1.getGrade()));
            } else if (request.getSort().equals("price-desc")) {
                Collections.sort(orderedHotels, (o1, o2) -> (o2.getHotelMinValue() - o1.getHotelMinValue()));
            }
            int lastIndex;
            if (orderedHotels.size() < ((request.getPage() - 1) * 10 + 10)) {
                lastIndex = orderedHotels.size() - 1;
            } else {
                lastIndex = ((request.getPage() - 1) * 10 + 10);
            }
            if (hotelQueryResult != null)
                orderedHotels.add(0, hotelQueryResult);
            List<HotelResult> hotelResults = orderedHotels.subList((request.getPage() - 1) * 10, lastIndex + 1);
            result = new Result(hotelResults, new RequestQuery(request.getCity(), (to.getDay() - from.getDay()), request.getGuest(),
                    request.getRooms(), DataConverter.farsiDate(request.getFrom()), DataConverter.farsiDate(request.getTo()), DataConverter.sortConv(request.getSort()), request.getPage())
                    , orderedHotels.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private Map<String, OTAEntity> getOTAs() {
        Map<String, OTAEntity> map = new HashMap<>();
        List<OTAEntity> all = otaRepository.findAll();
        for (OTAEntity otaEntity : all) {
            map.put(otaEntity.getName(), otaEntity);
        }
        return map;
    }

    private RoomPriceInfo calculateMinPrice(String key, Map<Integer, Integer> minRoomTypes, Map<Integer, String> minRoomNames) throws Exception {
        Map<Integer, ArrayList<Integer>> result = new HashMap<>();
        ArrayList<ArrayList<Integer>> categories = GuestRoomMatch.match.get(key);
        int minPrice = Integer.MAX_VALUE;
        ArrayList<String> selectedCategory = null;
        for (ArrayList<Integer> category : categories) {
            ArrayList<String> roomsCategory = new ArrayList<>();
            int value = 0;
            for (Integer roomType : category) {
                if (minRoomNames.get(roomType) == null) {
                    value = Integer.MAX_VALUE;
                    break;
                }
                value += minRoomTypes.get(roomType);
                roomsCategory.add(minRoomNames.get(roomType));
            }
            if (value < minPrice && value > 0) {
                minPrice = value;
                selectedCategory = roomsCategory;
            }
        }
        ArrayList<ProposedRoom> rooms = new ArrayList<>();
        Map<String, List<String>> collect = selectedCategory.stream().collect(Collectors.groupingBy(w -> w));
        for (Map.Entry<String, List<String>> entry : collect.entrySet()) {
            String name = entry.getKey();
            if (name.length() > 15) {
                String substring = name.substring(0, 14);
                name = substring + "...";
            }
            ProposedRoom proposedRoom = new ProposedRoom(name, entry.getValue().size());
            rooms.add(proposedRoom);
        }
        return new RoomPriceInfo(minPrice, rooms);
    }

    private Map<Integer, Integer> getRoomTypes() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, Integer.MAX_VALUE);
        map.put(2, Integer.MAX_VALUE);
        map.put(3, Integer.MAX_VALUE);
        map.put(4, Integer.MAX_VALUE);
        map.put(5, Integer.MAX_VALUE);
        map.put(6, Integer.MAX_VALUE);
        map.put(7, Integer.MAX_VALUE);
        map.put(8, Integer.MAX_VALUE);
        map.put(9, Integer.MAX_VALUE);
        return map;
    }

    public List<Image> findImages(String hotelId) {
        Optional<Hotel> hotelById = hotelRepository.findById(hotelId);
        List<Image> images = hotelById.get().getImages();
        for (Image image : images) {
            image.setSrc(image.getSrc().replace("/", "-"));
        }
        return images;
    }

    public String getRedirectDate(String ota, String from, String to) {
        String snapptrip = "?adults=1&date_from=%s&date_to=%s";
        String jabama = "?checkIn=%s&checkOut=%s";
        String jainjas = "?from=%s&to=%s&searched=true&IsCertain=false&star=0";
        Calendar calFrom = Calendar.getInstance();
        calFrom.setTime(DataConverter.jalaliToJavaDate(from));
        Calendar calTo = Calendar.getInstance();
        calTo.setTime(DataConverter.jalaliToJavaDate(to));
        String url = null;
        switch (ota) {
            case "snapptrip":
                url = String.format(snapptrip, calFrom.get(Calendar.YEAR) + "-" + (calFrom.get(Calendar.MONTH) + 1) + "-" + calFrom.get(Calendar.DAY_OF_MONTH),
                        calTo.get(Calendar.YEAR) + "-" + (calTo.get(Calendar.MONTH) + 1) + "-" + calTo.get(Calendar.DAY_OF_MONTH));
                break;
            case "jabama":
                url = String.format(jabama, from.replace("/", ""), to.replace("/", ""));
                break;
            case "jainjas":
                url = String.format(jainjas, from, to);
                break;
            case "eghamat24":
                url = "";
                break;
        }
        return url;
    }
}
