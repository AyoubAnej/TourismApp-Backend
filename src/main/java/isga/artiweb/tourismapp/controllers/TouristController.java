package isga.artiweb.tourismapp.controllers;

import isga.artiweb.tourismapp.dto.TouristDTO;
import isga.artiweb.tourismapp.services.TouristService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tourist")
@AllArgsConstructor
public class TouristController {
    TouristService touristService;

    @PostMapping("/create")
    public ResponseEntity<TouristDTO> createTourist(@RequestBody TouristDTO touristDTO){
        TouristDTO createdTourist = touristService.createTourist(touristDTO);

        return new ResponseEntity<TouristDTO>(createdTourist, HttpStatus.CREATED);
    }

    @GetMapping("/getAllTouristByBookingId/{bookingId}")
    public ResponseEntity<List<TouristDTO>> getTouristByBookingId(@PathVariable Integer bookingId){
        List<TouristDTO> touristDTOList = touristService.getTouristByBId(bookingId);

        return new ResponseEntity<List<TouristDTO>>(touristDTOList, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TouristDTO>> getAllTourists(){
        List<TouristDTO> getTourists = touristService.getAllTourist();

        return new ResponseEntity<List<TouristDTO>>(getTourists, HttpStatus.OK);
    }
}
