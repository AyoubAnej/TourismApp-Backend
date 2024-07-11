package isga.artiweb.tourismapp.repositories;

import isga.artiweb.tourismapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByEmail(String Email);
}
