package isga.artiweb.tourismapp.services;

import isga.artiweb.tourismapp.dto.TouristDTO;

import java.util.List;

public interface TouristService {

    TouristDTO createTourist(TouristDTO touristDTO);

    //TouristDTO updateTourist(TouristDTO tourist, Long touristId);

    List<TouristDTO> getTouristByBId(Integer bookingId);

    // List<TouristDTO> getTouristByBookingId(Long bookingId);
    List<TouristDTO> getAllTourist();

    //void deleteTouristById(Long touristId);
}
