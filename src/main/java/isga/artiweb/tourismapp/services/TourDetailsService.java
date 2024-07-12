package isga.artiweb.tourismapp.services;

import isga.artiweb.tourismapp.dto.TourDetailsDTO;
import isga.artiweb.tourismapp.entities.TourTypeEnum;

import java.util.List;

public interface TourDetailsService {

    TourDetailsDTO saveTourDetails(TourDetailsDTO tourDetailsDTO);

    TourDetailsDTO updateTourDetails(TourDetailsDTO tourDetailsDTO, Integer tourId);

    TourDetailsDTO getTourDetailsById(Integer tourId);

    List<TourDetailsDTO> getAllToursDetails();

    void deleteTourDetailsById(Integer tourId);

    List<TourDetailsDTO> getToursByDestination(String destination);

    List<TourDetailsDTO> getToursByTourType(TourTypeEnum tourType);

    List<TourDetailsDTO> findTourByBudget();

    List<TourDetailsDTO> findTourByDuration();
}
