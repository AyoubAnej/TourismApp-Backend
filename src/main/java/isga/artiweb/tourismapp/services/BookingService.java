package isga.artiweb.tourismapp.services;

import isga.artiweb.tourismapp.dto.BookingDTO;
import isga.artiweb.tourismapp.dto.TouristDTO;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    BookingDTO createBooking(BookingDTO bookingDTO, Integer userId, Integer tourDetailId, List<TouristDTO> touristDTOS);

    void deleteBookingById(Integer bookingId);

    BookingDTO getBookingById(Integer bookingId);

    List<BookingDTO> getAllBooking();

    List<BookingDTO> getBookingsByTourID(Integer tourId);

    List<BookingDTO> getBookingsByUserId(Integer userId);

    List<BookingDTO> getBookingByDuration(LocalDate startDate, LocalDate lastDate);

}
