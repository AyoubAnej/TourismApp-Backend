package isga.artiweb.tourismapp.repositories;

import isga.artiweb.tourismapp.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
