package isga.artiweb.tourismapp.Controllers;

import isga.artiweb.tourismapp.dto.ApiResponse;
import isga.artiweb.tourismapp.dto.FeedbackDTO;
import isga.artiweb.tourismapp.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")

public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<FeedbackDTO> createFeedback(@RequestBody FeedbackDTO feedbackdto,@PathVariable Integer userId) {
        FeedbackDTO feedbackCreated = this.feedbackService.createFeedback(feedbackdto,userId);
        return new ResponseEntity<FeedbackDTO>(feedbackCreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{feedbackId}")
    public ResponseEntity<ApiResponse> deleteFeedback(@PathVariable Integer feedbackId) {
        this.feedbackService.deleteFeedbackById(feedbackId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Feedback is deleted sucessfully", true), HttpStatus.OK);
    }



    @GetMapping("/getall")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
        List<FeedbackDTO> getallFeedbacks = this.feedbackService.getAllFeedback();
        return new ResponseEntity<List<FeedbackDTO>>(getallFeedbacks, HttpStatus.OK);
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<FeedbackDTO>> getByUserId(@PathVariable Integer userId) {
        List<FeedbackDTO> getFeedbacks = this.feedbackService.getByUserId(userId);
        return new ResponseEntity<List<FeedbackDTO>>(getFeedbacks, HttpStatus.OK);
    }
}
