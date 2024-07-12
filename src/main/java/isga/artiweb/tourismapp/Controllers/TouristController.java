package isga.artiweb.tourismapp.Controllers;

import isga.artiweb.tourismapp.dto.TouristDTO;
import isga.artiweb.tourismapp.services.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tourist")
public class TouristController {
    @Autowired
    TouristService touristService;

    @PostMapping("/create")
    public ResponseEntity<TouristDTO> createTourist(@RequestBody TouristDTO touristdto) {
        TouristDTO createdTourist = this.touristService.createTourist(touristdto);
        return new ResponseEntity<TouristDTO>(createdTourist, HttpStatus.CREATED);
    }
    @GetMapping("/getAllTouristByBookingId/{bookingId}")
    public ResponseEntity<List<TouristDTO>> getTouristByBookingId(@PathVariable Integer bookingId) {
        List<TouristDTO>getTourist = this.touristService.getTouristByBId(bookingId);
        return new ResponseEntity<List<TouristDTO>>(getTourist, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<TouristDTO>> getAllTourist() {
        List<TouristDTO> getTourist = this.touristService.getAllTourist();
        return new ResponseEntity<List<TouristDTO>>(getTourist, HttpStatus.OK);
    }

}
