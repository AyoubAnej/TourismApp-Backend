package isga.artiweb.tourismapp.dto;

import isga.artiweb.tourismapp.entities.TourTypeEnum;
import isga.artiweb.tourismapp.entities.TransportationMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TourDetailsDTO {
    private Integer tourId;
    private String tourName;
    private String tourImage;
    private String sourceCity;
    private String destinationCity;
    private String activities;
    private Double bookingPrice;
    private String tourDetailInfo;
    private LocalDate tourStartDate;
    private LocalDate tourEndDate;
    private Integer maxSeats;
    private TransportationMode transportationMode;
    private TourTypeEnum tourType;
}
