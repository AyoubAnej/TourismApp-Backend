package isga.artiweb.tourismapp.services.Impl;

import isga.artiweb.tourismapp.dto.TouristDTO;
import isga.artiweb.tourismapp.entities.Tourist;
import isga.artiweb.tourismapp.exception.ResourceNotFoundException;
import isga.artiweb.tourismapp.repositories.TouristRepository;
import isga.artiweb.tourismapp.services.TouristService;
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
public class TouristServiceImpl implements TouristService {
    ModelMapper modelMapper;
    TouristRepository touristRepository;
    @Override
    public TouristDTO createTourist(TouristDTO touristDTO) {
        Tourist tourist = modelMapper.map(touristDTO, Tourist.class);
        Tourist createdTourist = touristRepository.save(tourist);

        return modelMapper.map(createdTourist, TouristDTO.class);
    }

    @Override
    public List<TouristDTO> getTouristByBId(Integer bookingId) {
        List<Tourist> touristList = touristRepository.findByBookingBookingId(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist", "bookingId", bookingId));
        List<TouristDTO> touristDTOList = touristList.stream()
                .map((tourist) -> modelMapper.map(tourist, TouristDTO.class))
                .collect(Collectors.toList());

        return touristDTOList;
    }

    @Override
    public List<TouristDTO> getAllTourist() {
        List<Tourist> touristList = touristRepository.findAll();
        List<TouristDTO> touristDTOList = touristList.stream()
                .map((tourist) -> modelMapper.map(tourist, TouristDTO.class))
                .collect(Collectors.toList());

        return touristDTOList;
    }
}
