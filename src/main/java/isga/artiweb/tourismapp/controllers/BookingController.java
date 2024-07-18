package isga.artiweb.tourismapp.controllers;

import isga.artiweb.tourismapp.dto.ApiResponse;
import isga.artiweb.tourismapp.dto.BookingDTO;
import isga.artiweb.tourismapp.dto.BookingWrapper;
import isga.artiweb.tourismapp.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    BookingService bookingService;

    @PostMapping("/createBooking/tour/{tourId}/user/{userId}")
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingWrapper bookingWrapper, @PathVariable Integer tourId, @PathVariable Integer userId){
        BookingDTO bookingCreated = bookingService.createBooking(bookingWrapper.getBookingDto(),tourId, userId, bookingWrapper.getTouristDtoList());

        return new ResponseEntity<BookingDTO>(bookingCreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<ApiResponse> deleteBooking(@PathVariable Integer bookingId){
        bookingService.deleteBookingById(bookingId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Booking is deleted successfully", false), HttpStatus.OK);

    }

    @GetMapping("/getAllByUserId/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookingByUserId(@PathVariable Integer userId){
        List<BookingDTO> bookingDTO = bookingService.getBookingsByUserId(userId);

        return new ResponseEntity<List<BookingDTO>>(bookingDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{bookingId}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable Integer bookingId){
        BookingDTO getBooking = bookingService.getBookingById(bookingId);

        return new ResponseEntity<BookingDTO>(getBooking, HttpStatus.OK);
    }

    //consulting all the bookings
    @GetMapping("/getAllBookings")
    public ResponseEntity<List<BookingDTO>> getAllBookings(){
        List<BookingDTO> bookingDTO = bookingService.getAllBooking();

        return new ResponseEntity<List<BookingDTO>>(bookingDTO, HttpStatus.OK);
    }

    @GetMapping("/getAllByTourId/{tourId}")
    public ResponseEntity<List<BookingDTO>> getBookingByTourId(@PathVariable Integer tourId){
        List<BookingDTO> bookingDTO = bookingService.getBookingsByTourID(tourId);

        return new ResponseEntity<List<BookingDTO>>(bookingDTO, HttpStatus.OK);
    }

    @GetMapping("/getByDate/{startDate}/{endDate}")
    public ResponseEntity<List<BookingDTO>> getBookingsByDuration(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        List<BookingDTO> bookingDTO = bookingService.getBookingByDuration(startDate, endDate);

        return new ResponseEntity<List<BookingDTO>>(bookingDTO, HttpStatus.OK);
    }

}
