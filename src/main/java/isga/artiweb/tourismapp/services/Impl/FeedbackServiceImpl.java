package isga.artiweb.tourismapp.services.Impl;

import isga.artiweb.tourismapp.dto.FeedbackDTO;
import isga.artiweb.tourismapp.entities.Feedback;
import isga.artiweb.tourismapp.exception.ResourceNotFoundException;
import isga.artiweb.tourismapp.repositories.FeedbackRepository;
import isga.artiweb.tourismapp.services.FeedbackService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    ModelMapper modelMapper;
    FeedbackRepository feedbackRepository;
    @Override
    public void deleteFeedbackById(Integer feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ResourceNotFoundException("feedback", "feedbackId", feedbackId));

        feedbackRepository.delete(feedback);
    }

    @Override
    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO, Integer userId) {
        Feedback feedback = modelMapper.map(feedbackDTO, Feedback.class);
        Feedback createdFeedback = feedbackRepository.save(feedback);

        return modelMapper.map(createdFeedback, FeedbackDTO.class);
    }

    @Override
    public List<FeedbackDTO> getAllFeedback() {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        List<FeedbackDTO> feedbackDTOList = feedbackList.stream()
                .map((feedback) -> modelMapper.map(feedback, FeedbackDTO.class))
                .collect(Collectors.toList());

        return feedbackDTOList;
    }

    @Override
    public List<FeedbackDTO> getByUserId(Integer userId) {

        List<Feedback> feedbackList = feedbackRepository.findByUserUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("feedbacks", "userId", userId));

//        List<FeedbackDTO> feedbackDTOList = feedbackList.stream()
//                .map((feedback) -> modelMapper.map(feedback, FeedbackDTO.class))
//                .collect(Collectors.toList());

        if (feedbackList.isEmpty()){
            throw new ResourceNotFoundException("feedbacks", "userId", userId);
        }
        List<FeedbackDTO> feedbackDTOList = new ArrayList<>();

        for(Feedback f : feedbackList){
            feedbackDTOList.add(modelMapper.map(f,FeedbackDTO.class));
        }

        return feedbackDTOList;
    }
}
