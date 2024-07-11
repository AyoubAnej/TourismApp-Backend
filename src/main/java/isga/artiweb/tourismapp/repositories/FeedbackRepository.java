package isga.artiweb.tourismapp.repositories;

import isga.artiweb.tourismapp.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
