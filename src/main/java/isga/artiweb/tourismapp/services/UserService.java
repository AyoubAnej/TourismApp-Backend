package isga.artiweb.tourismapp.services;

import isga.artiweb.tourismapp.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user, Long userId);

    UserDTO getUserByEmail(String email);

    UserDTO getUserById(Long userId);

    List<UserDTO> getAllUsers();

    void deleteUserById(Long ID);
}
