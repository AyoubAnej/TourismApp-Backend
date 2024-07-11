package isga.artiweb.tourismapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ourusers")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Invalid Email format")
    private String email;

    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Invalid Password!")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "first_name", nullable = false)
    @Length(min = 2, max = 20, message = "Invalid length of the first name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Length(min = 2, max = 20, message = "Invalid length of the last name")
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    @Past(message = "dob should be in past")
    private LocalDate dob;

    @Column(name = "address", nullable = false)
    @Length(max = 100, message = "Invalid length of the address")
    private String address;

    @Column(name = "phone_no", nullable = false)
    private Long phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Feedback> feedbackList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookingList;

}
