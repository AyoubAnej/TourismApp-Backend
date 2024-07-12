package isga.artiweb.tourismapp.repositories;

import isga.artiweb.tourismapp.entities.Booking;
import isga.artiweb.tourismapp.entities.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TouristRepository extends JpaRepository<Tourist, Integer> {

    Optional<List<Tourist>> findByBookingBookingId(Integer bookingId);
}
