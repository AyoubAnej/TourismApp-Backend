package isga.artiweb.tourismapp;

import isga.artiweb.tourismapp.entities.Role;
import isga.artiweb.tourismapp.entities.User;
import isga.artiweb.tourismapp.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class TourismAppApplication {

    @Bean
    @Primary
    public BCryptPasswordEncoder mainPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, BCryptPasswordEncoder mainPasswordEncoder){
        return  args -> {
          userRepository.save(User.builder()
                  .userId(null)
                  .email("admin@example.com")
                  .password(mainPasswordEncoder.encode("P@ssw0rd")) //P@ssw0rd
                  .role(Role.ADMIN)
                  .firstName("admin")
                  .lastName("admin")
                  .dob(LocalDate.of(1990, 1, 1))
                  .address("123 Main St, Anytown, USA")
                  .phoneNumber(1234567890L)
                  .build()
          );
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(TourismAppApplication.class, args);
    }

}
