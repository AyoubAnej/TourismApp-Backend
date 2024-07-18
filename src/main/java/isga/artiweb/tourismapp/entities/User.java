package isga.artiweb.tourismapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ourusers")
@Builder
public class User implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Invalid Email format")
    private String email;

//    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Invalid Password!")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
