package isga.artiweb.tourismapp.repositories;

import isga.artiweb.tourismapp.entities.Booking;
import isga.artiweb.tourismapp.entities.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouristRepository extends JpaRepository<Tourist, Integer> {
}
