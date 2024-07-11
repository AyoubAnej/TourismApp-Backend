package isga.artiweb.tourismapp.dto;

import isga.artiweb.tourismapp.entities.IdProof;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TouristDTO {
    private Integer touristId;
    private Integer age;
    private String touristName;
    private IdProof idProof;
    private String idProofNo;

}
