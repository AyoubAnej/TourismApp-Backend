package isga.artiweb.tourismapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BookingWrapper {
    private BookingDTO bookingDto;
    private List<TouristDTO> touristDtoList;
}
