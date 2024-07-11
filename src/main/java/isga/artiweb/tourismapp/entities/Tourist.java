package isga.artiweb.tourismapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tourist_id")
    private Integer touristId;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "id_proof", nullable = false)
    private IdProof idProof;

    @Column(name = "id_proof_no", nullable = false)
    private String idProofNo;

    @Length(min = 2, max = 20, message = "Invalid length of tourist name")
    @Column(name = "tourist_name", nullable = false)
    private String touristName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
