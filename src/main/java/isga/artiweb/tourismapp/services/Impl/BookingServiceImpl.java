package isga.artiweb.tourismapp.services.Impl;

import isga.artiweb.tourismapp.dto.BookingDTO;
import isga.artiweb.tourismapp.dto.TourDetailsDTO;
import isga.artiweb.tourismapp.dto.TouristDTO;
import isga.artiweb.tourismapp.entities.Booking;
import isga.artiweb.tourismapp.entities.TourDetails;
import isga.artiweb.tourismapp.entities.Tourist;
import isga.artiweb.tourismapp.entities.User;
import isga.artiweb.tourismapp.exception.ResourceNotFoundException;
import isga.artiweb.tourismapp.repositories.BookingRepository;
import isga.artiweb.tourismapp.repositories.TourDetailsRepository;
import isga.artiweb.tourismapp.repositories.TouristRepository;
import isga.artiweb.tourismapp.repositories.UserRepository;
import isga.artiweb.tourismapp.services.BookingService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class BookingServiceImpl implements BookingService {
    ModelMapper modelMapper;
    BookingRepository bookingRepository;
    UserRepository userRepository;
    TourDetailsRepository tourRepo;
    TouristRepository touristRepo;
    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO, Integer userId, Integer tourDetailId, List<TouristDTO> touristDTOS) {
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
        TourDetails tour = tourRepo.findById(tourDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("tour", "tourId", tourDetailId));

        booking.setUser(user);
        booking.setTourDetails(tour);

        Booking createdBooking = bookingRepository.save(booking);

        Integer bookingId = createdBooking.getBookingId();

        Booking bookingObj = bookingRepository.findById(bookingId).get();

        List<Tourist> tourists = new ArrayList<>();

        for (TouristDTO tr : touristDTOS){
            Tourist tourist = modelMapper.map(tr, Tourist.class);
            touristRepo.save(tourist);
            tourist.setBooking(bookingObj);
            //saving into the tourists List
            tourists.add(tourist);
        }
        tourists.stream().forEach(tourist -> System.err.println(tourist + "Tourist added"));

        bookingObj.setTouristList(tourists);

        tour.setMaxSeats(tour.getMaxSeats() - createdBooking.getSeatCount());

        BookingDTO updatedBookingDto = modelMapper.map(bookingRepository.save(bookingObj), BookingDTO.class);

        return updatedBookingDto;
    }

    @Override
    public void deleteBookingById(Integer bookingId) {
        Booking deletebooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("booking", "bookingId", bookingId));
        bookingRepository.delete(deletebooking);
    }

    @Override
    public BookingDTO getBookingById(Integer bookingId) {
        Booking getBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("booking", "bookingId", bookingId));

        return modelMapper.map(getBooking, BookingDTO.class);
    }

    @Override
    public List<BookingDTO> getAllBooking() {
        List<Booking> getAllBooking = bookingRepository.findAll();
        List<BookingDTO> getgetAllBookingDTO = getAllBooking.stream()
                .map((getBooking) -> modelMapper.map(getBooking, BookingDTO.class))
                .collect(Collectors.toList());

        return getgetAllBookingDTO;
    }

    @Override
    public List<BookingDTO> getBookingsByTourID(Integer tourId) {
        List<Booking> bookings = bookingRepository.findByTourDetailsTourId(tourId);
//        List<BookingDTO> bookingDTOS  = bookings.stream()
//                .map((booking) -> modelMapper.map(booking, BookingDTO.class))
//                .collect(Collectors.toList());
        List<BookingDTO> dtoList = new ArrayList<>();
        bookings.forEach(
                booking -> {
                    BookingDTO dto = modelMapper.map(booking, BookingDTO.class);
                    dto.setTourDetails(modelMapper.map(booking.getTourDetails(), TourDetailsDTO.class));
                    dtoList.add(dto);
                }
        );

        return dtoList;
    }

    @Override
    public List<BookingDTO> getBookingsByUserId(Integer userId) {
        List<Booking> bookings = bookingRepository.findByUserUserId(userId);
        List<BookingDTO> bookingDTOS = bookings.stream()
                .map((booking) -> modelMapper.map(booking, BookingDTO.class)).collect(Collectors.toList());

        return bookingDTOS;
    }

    @Override
    public List<BookingDTO> getBookingByDuration(LocalDate startDate, LocalDate lastDate) {
        List<Booking> getAllBooking = bookingRepository.findByBookingDateBetween(startDate, lastDate);
        List<BookingDTO> getAllBookingDto = getAllBooking.stream()
                .map((getBooking) -> modelMapper.map(getBooking, BookingDTO.class)).collect(Collectors.toList());
        return getAllBookingDto;
    }
}
