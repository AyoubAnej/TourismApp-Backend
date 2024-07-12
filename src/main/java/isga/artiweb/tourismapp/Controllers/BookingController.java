package isga.artiweb.tourismapp.Controllers;

import isga.artiweb.tourismapp.dto.ApiResponse;
import isga.artiweb.tourismapp.dto.BookingDTO;
import isga.artiweb.tourismapp.dto.BookingWrapper;
import isga.artiweb.tourismapp.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/booking")

public class BookingController {
    @Autowired
    BookingService bookingService;

    @PostMapping("/createBooking/tour/{tourId}/user/{userId}")
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingWrapper bookingWrapper , @PathVariable Integer tourId, @PathVariable Integer userId) {
        BookingDTO bookingcreated = this.bookingService.createBooking(bookingWrapper.getBookingDto(),tourId, userId,bookingWrapper.getTouristDtoList());
        return new ResponseEntity<BookingDTO>(bookingcreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<ApiResponse> deleteBooking(@PathVariable Integer bookingId) {
        this.bookingService.deleteBookingById(bookingId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Booking is deleted sucessfully", false), HttpStatus.OK);
    }
    @GetMapping("/getAllbyuserId/{userId}")
    public ResponseEntity<List<BookingDTO>>getBookingByUserId(@PathVariable Integer userId) {
        List<BookingDTO> bookingDto =this.bookingService.getBookingsByUserId(userId);
        return new ResponseEntity<List<BookingDTO>>(bookingDto,HttpStatus.OK);
    }
    @GetMapping("/get/{bookingId}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable Integer bookingId) {
        BookingDTO getBooking = this.bookingService.getBookingById(bookingId);
        return new ResponseEntity<BookingDTO>(getBooking, HttpStatus.OK);
    }

    // for checking purpose only
    @GetMapping("/getallbookings")
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookingDto = this.bookingService.getAllBooking();
        return new ResponseEntity<List<BookingDTO>>(bookingDto, HttpStatus.OK);
    }
    @GetMapping("/getAllByTourId/{tourId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByTourID(@PathVariable Integer tourId ){
        List<BookingDTO> bookingDto =this.bookingService.getBookingsByTourID(tourId);
        return new ResponseEntity<List<BookingDTO>>(bookingDto,HttpStatus.OK);

    }
    // for getting the booking by tour date

    @GetMapping("/getbyDate/{startdate}/{lastdate}")
    public ResponseEntity<List<BookingDTO>> getBookingsByDuration(LocalDate startDate, LocalDate lastDate){
        List<BookingDTO> bookingdto = this.bookingService.getBookingByDuration(startDate,  lastDate);
        return new ResponseEntity<List<BookingDTO>>(bookingdto, HttpStatus.OK);
    }
}
