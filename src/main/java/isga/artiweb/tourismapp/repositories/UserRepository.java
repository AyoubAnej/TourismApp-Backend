package isga.artiweb.tourismapp.repositories;

import isga.artiweb.tourismapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
