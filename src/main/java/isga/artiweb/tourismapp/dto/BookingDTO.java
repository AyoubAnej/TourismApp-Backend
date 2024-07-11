package isga.artiweb.tourismapp.dto;

import isga.artiweb.tourismapp.entities.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDTO {
    private Integer bookingId;
    private LocalDate bookingDate;
    private PaymentStatus paymentStatus;
    private Integer seatCount;
    private Long totalAmount;
    //	private TourDetails tourDetails;
    private TourDetailsDTO tourDetails;
}
