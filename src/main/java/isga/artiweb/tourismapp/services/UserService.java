package isga.artiweb.tourismapp.services;

import isga.artiweb.tourismapp.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userdto);

    UserDTO updateUser(UserDTO userDTO, Integer userId);

    UserDTO getUserByEmail(String email);

    UserDTO getUserById(Integer userId);

    List<UserDTO> getAllUsers();

    void deleteUserById(Integer userId);
}
