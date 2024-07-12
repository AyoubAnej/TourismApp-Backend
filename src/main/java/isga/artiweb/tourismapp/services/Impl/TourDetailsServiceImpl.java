package isga.artiweb.tourismapp.services.Impl;

import isga.artiweb.tourismapp.dto.TourDetailsDTO;
import isga.artiweb.tourismapp.entities.TourDetails;
import isga.artiweb.tourismapp.entities.TourTypeEnum;
import isga.artiweb.tourismapp.exception.ResourceNotFoundException;
import isga.artiweb.tourismapp.repositories.TourDetailsRepository;
import isga.artiweb.tourismapp.services.TourDetailsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class TourDetailsServiceImpl implements TourDetailsService {
    ModelMapper modelMapper;
    TourDetailsRepository tourDetailsRepository;

    @Override
    public TourDetailsDTO saveTourDetails(TourDetailsDTO tourDetailsDTO) {
        TourDetails tourDetails = modelMapper.map(tourDetailsDTO, TourDetails.class);
        TourDetails savedTourDetails = tourDetailsRepository.save(tourDetails);

        return modelMapper.map(savedTourDetails, TourDetailsDTO.class);
    }

    @Override
    public TourDetailsDTO updateTourDetails(TourDetailsDTO tourDetailsDTO, Integer tourId) {
        TourDetails tourDetails = tourDetailsRepository.findById(tourId)
                .orElseThrow(() -> new ResourceNotFoundException("Tour Details", "tourId", tourId));

        tourDetails.setTourName(tourDetailsDTO.getTourName());
        tourDetails.setTourDetailInfo(tourDetailsDTO.getTourDetailInfo());
        tourDetails.setTourImage(tourDetailsDTO.getTourImage());
        tourDetails.setTourType(tourDetailsDTO.getTourType());
        tourDetails.setActivities(tourDetailsDTO.getActivities());
        tourDetails.setSourceCity(tourDetailsDTO.getSourceCity());
        tourDetails.setDestinationCity(tourDetailsDTO.getDestinationCity());
        tourDetails.setTourStartDate(tourDetailsDTO.getTourStartDate());
        tourDetails.setTourEndDate(tourDetailsDTO.getTourEndDate());
        tourDetails.setMaxSeats(tourDetailsDTO.getMaxSeats());
        tourDetails.setBookingPrice(tourDetailsDTO.getBookingPrice());
        tourDetails.setTransportationMode(tourDetailsDTO.getTransportationMode());

        TourDetails updatedTourDetails = tourDetailsRepository.save(tourDetails);


        return modelMapper.map(updatedTourDetails, TourDetailsDTO.class);
    }

    @Override
    public TourDetailsDTO getTourDetailsById(Integer tourId) {
        TourDetails tourDetails = tourDetailsRepository.findById(tourId)
                .orElseThrow(() -> new ResourceNotFoundException("Tour Details", "tourId", tourId));

        return modelMapper.map(tourDetails, TourDetailsDTO.class);
    }

    @Override
    public List<TourDetailsDTO> getAllToursDetails() {
        List<TourDetails> tourDetailsList = tourDetailsRepository.findAll();
        List<TourDetailsDTO> tourDetailsDTOList = tourDetailsList.stream()
                .map((tourDetails) -> modelMapper.map(tourDetails, TourDetailsDTO.class))
                .collect(Collectors.toList());
        return tourDetailsDTOList;
    }

    @Override
    public void deleteTourDetailsById(Integer tourId) {
        TourDetails tourDetails = tourDetailsRepository.findById(tourId)
                .orElseThrow(() -> new ResourceNotFoundException("Tour Details", "tourId", tourId));
        tourDetailsRepository.delete(tourDetails);

    }

    @Override
    public List<TourDetailsDTO> getToursByDestination(String destination) {
        List<TourDetails> tourDetailsList = tourDetailsRepository.findByDestination(destination)
                .orElseThrow(() -> new ResourceNotFoundException("Tour details", "destination", destination));
        List<TourDetailsDTO> tourDetailsDTOList = tourDetailsList.stream()
                .map((tourDetails) -> modelMapper.map(tourDetails, TourDetailsDTO.class))
                .collect(Collectors.toList());

        return tourDetailsDTOList;
    }

    @Override
    public List<TourDetailsDTO> getToursByTourType(TourTypeEnum tourType) {
        List<TourDetails> tourDetailsList = tourDetailsRepository.findByTourType(tourType)
                .orElseThrow(() -> new ResourceNotFoundException("Tour details", "tourType", tourType));
        List<TourDetailsDTO> tourDetailsDTOList = tourDetailsList.stream()
                .map((tourDetails) -> modelMapper.map(tourDetails, TourDetailsDTO.class))
                .collect(Collectors.toList());

        return tourDetailsDTOList;

    }

    @Override
    public List<TourDetailsDTO> findTourByBudget() {
        List<TourDetails> tourDetailsList = tourDetailsRepository.findTourByBudget();

        List<TourDetailsDTO> tourDetailsDTOList = tourDetailsList.stream()
                .map((tourDetails) -> modelMapper.map(tourDetails, TourDetailsDTO.class))
                .collect(Collectors.toList());

        return tourDetailsDTOList;
    }

    @Override
    public List<TourDetailsDTO> findTourByDuration() {
        List<TourDetails> tourDetailsList = tourDetailsRepository.findByDuration();

        List<TourDetailsDTO> tourDetailsDTOList = tourDetailsList.stream()
                .map((tourDetails) -> modelMapper.map(tourDetails, TourDetailsDTO.class))
                .collect(Collectors.toList());

        return tourDetailsDTOList;
    }
}
