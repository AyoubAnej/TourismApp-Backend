package isga.artiweb.tourismapp.repositories;

import isga.artiweb.tourismapp.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByTourDetailsTourId(Integer tourId);
    List<Booking> findByUserUserId(Integer userId);
    List<Booking> findByBookingDateBetween(LocalDate startDate, LocalDate endDate);
}
