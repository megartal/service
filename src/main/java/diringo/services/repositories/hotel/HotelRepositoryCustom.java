package diringo.services.repositories.hotel;

import diringo.services.documents.Hotel;

import java.util.List;

/**
 * @Author Akbar
 * @DATE 7/1/2018.
 */
public interface HotelRepositoryCustom {
    List<Hotel> findHotel(String term);
}
