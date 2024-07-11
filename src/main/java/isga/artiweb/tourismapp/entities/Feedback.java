package isga.artiweb.tourismapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "email", nullable = false)
    @Email(message = "Invalid Email format")
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "rating")
    @Min(value = 0)
    @Max(value = 5)
    @Digits(fraction = 0, integer = 1, message = "The rating can be given only 0 to 5 ")
    private Integer rating;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
