package isga.artiweb.tourismapp.repositories;

import isga.artiweb.tourismapp.entities.TourDetails;
import isga.artiweb.tourismapp.entities.TourTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TourDetailsRepository extends JpaRepository<TourDetails, Integer> {

    Optional<List<TourDetails>> findByDestinationCity(String destination);
    @Query(value="select * from tour_details where tour_start >= DATE(NOW()) ORDER BY booking_price", nativeQuery = true)
    List<TourDetails> findTourByBudget();
    @Query(value = "select *, tour_end_date - tour_start_date AS duration from tour_details where tour_start >= DATE(NOW()) ORDER BY duration", nativeQuery = true)
    List<TourDetails> findByDuration();

    Optional<List<TourDetails>> findByTourType(TourTypeEnum tourType);
}
