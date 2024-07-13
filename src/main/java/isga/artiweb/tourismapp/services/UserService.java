package isga.artiweb.tourismapp.services;

import isga.artiweb.tourismapp.dto.ReqRes;
import isga.artiweb.tourismapp.dto.UserDTO;
import isga.artiweb.tourismapp.entities.User;

import java.util.List;

public interface UserService {
    ReqRes register(ReqRes registrationRequest);

    ReqRes login(ReqRes loginRequest);

    ReqRes refreshToken(ReqRes refreshTokenRequest);

    ReqRes updateUser(User updatedUser, Integer userId);

//    UserDTO getUserByEmail(String email);

    ReqRes getUserById(Integer id);

    ReqRes getAllUsers();

    ReqRes deleteUserById(Integer userId);

    ReqRes getMyInfo(String email);
}
