package isga.artiweb.tourismapp.controllers;

import isga.artiweb.tourismapp.dto.ApiResponse;
import isga.artiweb.tourismapp.dto.FeedbackDTO;
import isga.artiweb.tourismapp.services.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
@AllArgsConstructor
public class FeedbackController {
    FeedbackService feedbackService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<FeedbackDTO> createFeedback(@RequestBody FeedbackDTO feedbackDTO, @PathVariable Integer userId){
        FeedbackDTO feedbackCreated = feedbackService.createFeedback(feedbackDTO, userId);

        return new ResponseEntity<FeedbackDTO>(feedbackCreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{feedbackId}")
    public ResponseEntity<ApiResponse> deleteFeedback(@PathVariable Integer feedbackId){
        feedbackService.deleteFeedbackById(feedbackId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Feedback is deleted successfully", false), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks(){
        List<FeedbackDTO> getAllFeedbacks = feedbackService.getAllFeedback();

        return new ResponseEntity<List<FeedbackDTO>>(getAllFeedbacks, HttpStatus.OK);
    }

    @GetMapping("/getByUserId/userId")
    public ResponseEntity<List<FeedbackDTO>> getByUserId(@PathVariable Integer userId){
        List<FeedbackDTO> getFeedbacks = feedbackService.getByUserId(userId);

        return new ResponseEntity<List<FeedbackDTO>>(getFeedbacks, HttpStatus.OK);

    }
}
