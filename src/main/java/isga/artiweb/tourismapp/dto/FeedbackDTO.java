package isga.artiweb.tourismapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class FeedbackDTO {
    private Integer feedbackId;
    private String comment;
    //	private Long userId;
    private String email;
    private String firstName;
    private Integer rating;

}
