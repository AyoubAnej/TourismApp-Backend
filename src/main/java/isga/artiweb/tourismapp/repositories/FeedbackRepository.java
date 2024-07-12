package isga.artiweb.tourismapp.repositories;

import isga.artiweb.tourismapp.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    Optional<List<Feedback>> findByUserUserId(Integer userId);
}
