package isga.artiweb.tourismapp.services;

import isga.artiweb.tourismapp.dto.FeedbackDTO;
import isga.artiweb.tourismapp.entities.Feedback;

import java.util.List;

public interface FeedbackService {

    void deleteFeedbackById(Integer feedbackId);

    FeedbackDTO createFeedback(FeedbackDTO feedbackDTO, Integer userId);

    List<FeedbackDTO> getAllFeedback();

    List<FeedbackDTO> getByUserId(Integer userId);
}
