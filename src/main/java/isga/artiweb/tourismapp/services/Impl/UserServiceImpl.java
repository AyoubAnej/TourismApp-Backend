package isga.artiweb.tourismapp.services.Impl;

import isga.artiweb.tourismapp.dto.UserDTO;
import isga.artiweb.tourismapp.entities.User;
import isga.artiweb.tourismapp.exception.ResourceNotFoundException;
import isga.artiweb.tourismapp.repositories.UserRepository;
import isga.artiweb.tourismapp.services.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    ModelMapper modelMapper;
    UserRepository userRepository;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User createdUser = userRepository.save(user);
        return modelMapper.map(createdUser, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->new ResourceNotFoundException("user", "userId", userId));
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());
        user.setDob(userDTO.getDob());
        user.setPhoneNumber(userDTO.getPhoneNo());
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("user", "email", email));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> allUsersDto = users.stream().map((user) -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return allUsersDto;
    }

    @Override
    public void deleteUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
        userRepository.delete(user);
    }
}
