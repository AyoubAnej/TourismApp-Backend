package isga.artiweb.tourismapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tour_details")
public class TourDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id")
    private Integer tourId;

    @Column(name = "tour_name", nullable = false)
    @Length(min = 2, max = 30, message = "Invalid length of the tour name")
    private String tourName;

    @Column(name = "tour_image", nullable = false)
    private String tourImage;

    @Column(name = "source_city", nullable = false)
    @NonNull
    @Length(min = 2, max = 30, message = "Invalid length of the source")
    private String sourceCity;

    @Column(name = "destination_city", nullable = false)
    @NonNull
    @Length(min = 2, max = 30, message = "Invalid length of the destination")
    private String destinationCity;

    @NonNull
    @Length(min = 2, max = 30, message = "Invalid length of the tour name")
    private String activities;

    @Column(name = "booking_price", nullable = false)
    @Min(value = 0, message = "The value must be positive")
    private Double bookingPrice;

    @Column(name = "tour_detail_info")
    @Length(min = 2, max = 50, message = "Invalid length of the tour description")
    private String tourDetailInfo;

    @Column(name = "tour_start_date", nullable = false)
    private LocalDate tourStartDate;

    @Column(name = "tour_end_date", nullable = false)
    // tour end date must be after tour start date validation required
    private LocalDate tourEndDate;

    @Column(name = "max_seats")
    @Min(value = 0)
    private Integer maxSeats;

    @Column(name = "transportation_mode")
    @Enumerated(EnumType.STRING)
    private TransportationMode transportationMode;

    @Column(name = "tour_type")
    @Enumerated(EnumType.STRING)
    private TourTypeEnum tourType;

    @OneToMany(mappedBy = "tourDetails", cascade = CascadeType.ALL)
    List<Booking> bookingList = new ArrayList<>();
}
