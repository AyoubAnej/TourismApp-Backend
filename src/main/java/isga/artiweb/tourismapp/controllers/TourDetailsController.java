package isga.artiweb.tourismapp.controllers;

import isga.artiweb.tourismapp.dto.ApiResponse;
import isga.artiweb.tourismapp.dto.TourDetailsDTO;
import isga.artiweb.tourismapp.entities.TourTypeEnum;
import isga.artiweb.tourismapp.services.TourDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tourdetails")
@AllArgsConstructor
public class TourDetailsController {
    TourDetailsService tourDetailsService;

    @PostMapping("/create")
    public ResponseEntity<TourDetailsDTO> saveTourDetails(@RequestBody TourDetailsDTO tourDetailsDTO){
        TourDetailsDTO savedTourDetails = tourDetailsService.saveTourDetails(tourDetailsDTO);

        return new ResponseEntity<TourDetailsDTO>(savedTourDetails, HttpStatus.CREATED);
    }

    @PutMapping("/update/{tourDetailId}")
    public ResponseEntity<TourDetailsDTO> updateTourDetails(@RequestBody TourDetailsDTO tourDetailsDTO, @PathVariable Integer tourDetailId){
        TourDetailsDTO updatedTourDetails = tourDetailsService.updateTourDetails(tourDetailsDTO, tourDetailId);

        return new ResponseEntity<TourDetailsDTO>(updatedTourDetails, HttpStatus.OK);
    }

    @GetMapping("/get/{tourDetailId}")
    public ResponseEntity<TourDetailsDTO> getTourDetailsById(@PathVariable Integer tourDetailId){
        TourDetailsDTO tourDetailsDTO = tourDetailsService.getTourDetailsById(tourDetailId);

        return new ResponseEntity<TourDetailsDTO>(tourDetailsDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{tourdetailId}")
    public ResponseEntity<ApiResponse> deleteTourDetailsById(@PathVariable Integer tourdetailId){
        tourDetailsService.deleteTourDetailsById(tourdetailId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Tour detail is deleted successfully!", false), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TourDetailsDTO>> getAllTourDetails(){
        List<TourDetailsDTO> allTourDetails = tourDetailsService.getAllToursDetails();

        return new ResponseEntity<List<TourDetailsDTO>>(allTourDetails, HttpStatus.OK);
    }

    @GetMapping("/getByDestination/{destination}")
    public ResponseEntity<List<TourDetailsDTO>> getTourDetailsByDestination(@PathVariable String destination){
        List<TourDetailsDTO> tourDetailsList = tourDetailsService.getToursByDestination(destination);

        return new ResponseEntity<List<TourDetailsDTO>>(tourDetailsList, HttpStatus.OK);
    }

    @GetMapping("/getByBudget")
    public ResponseEntity<List<TourDetailsDTO>> getTourDetailsByBudget(){
        List<TourDetailsDTO> tourDetailsDTOList = tourDetailsService.findTourByBudget();

        return new ResponseEntity<List<TourDetailsDTO>>(tourDetailsDTOList, HttpStatus.OK);
    }

    @GetMapping("/getByDuration")
    public ResponseEntity<List<TourDetailsDTO>> getTourDetailsByDuration(){
        List<TourDetailsDTO> tourDetailsDTOList = tourDetailsService.findTourByDuration();

        return new ResponseEntity<List<TourDetailsDTO>>(tourDetailsDTOList, HttpStatus.OK);
    }

    @GetMapping("/getByTourType/{tourType}")
    public ResponseEntity<List<TourDetailsDTO>> getTourDetailsByTourType(@PathVariable String tourType){
        List<TourDetailsDTO> tourDetailsDTOList = tourDetailsService.getToursByTourType(TourTypeEnum.valueOf(tourType));

        return new ResponseEntity<List<TourDetailsDTO>>(tourDetailsDTOList, HttpStatus.OK);
    }
}
