package isga.artiweb.tourismapp.services.Impl;

import isga.artiweb.tourismapp.dto.ReqRes;
import isga.artiweb.tourismapp.dto.UserDTO;
import isga.artiweb.tourismapp.entities.User;
import isga.artiweb.tourismapp.exception.ResourceNotFoundException;
import isga.artiweb.tourismapp.repositories.UserRepository;
import isga.artiweb.tourismapp.services.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JWTUtils jwtUtils;

    @Override
    public ReqRes register(ReqRes registrationRequest) {
        // Create a response object to hold the registration response
        ReqRes resp = new ReqRes();

        try {
            // Create a new user object from the registration request
            User user = new User();
            // Set user details from the registration request
            user.setFirstName(registrationRequest.getFirstName());
            user.setLastName(registrationRequest.getLastName());
            user.setEmail(registrationRequest.getEmail());
            user.setAddress(registrationRequest.getAddress());
            user.setRole(registrationRequest.getRole());
            user.setDob(registrationRequest.getDob());
            user.setPhoneNumber(registrationRequest.getPhoneNo());
            // Encrypt the user's password before storing it
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

            // Generate a JWT token for authentication
            var jwt = jwtUtils.generateToken(user);
            // Generate a refresh token for future authentication
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            // Save the user details in the repository
            User usersResult = userRepository.save(user);

            // If user is successfully saved
            if (usersResult.getUserId() != null) {
                // Set success response status code
                resp.setStatusCode(200);
                // Set JWT token in the response
                resp.setToken(jwt);
                // Set refresh token in the response
                resp.setRefreshToken(refreshToken);
                // Set expiration time for the tokens
                resp.setExpirationTime("24Hrs");
                // Set the saved user details in the response
                resp.setUser(usersResult);
                // Set success message in the response
                resp.setMessage("User Saved Successfully");
            }

        } catch (Exception e) {
            // If an exception occurs during registration
            // Set error response status code
            resp.setStatusCode(500);
            // Set error message in the response
            resp.setError(e.getMessage());
        }
        // Return the registration response
        return resp;

    }

    @Override
    public ReqRes login(ReqRes loginRequest) {
        ReqRes response = new ReqRes();
        try{
            authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                    loginRequest.getPassword())));
            Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
            if (userOptional.isPresent()){
                User user = userOptional.get();
                var jwt = jwtUtils.generateToken(user);
                var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRole(user.getRole());
                response.setRefreshToken(refreshToken);
                response.setExpirationTime("24Hrs");
                response.setMessage("Successfully Logged In");
            }else {
                response.setStatusCode(404);
                response.setMessage("user not found");
            }

        }catch(Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public ReqRes refreshToken(ReqRes refreshTokenRequest) {
        ReqRes response = new ReqRes();
        try {
            String userEmail = jwtUtils.extractUsername(refreshTokenRequest.getRefreshToken());
            Optional<User> userOptional = userRepository.findByEmail(userEmail);
            if (userOptional.isPresent()){
                User user = userOptional.get();
                if (jwtUtils.isTokenValid(refreshTokenRequest.getRefreshToken(), user)){
                    var jwt = jwtUtils.generateToken(user);
                    response.setStatusCode(200);
                    response.setToken(jwt);
                    response.setRefreshToken(refreshTokenRequest.getRefreshToken());
                    response.setExpirationTime("24Hrs");
                    response.setMessage("Successfully Refreshed token");
                }else {
                    response.setStatusCode(401); //Unauthorized
                    response.setMessage("Invalid refresh token");
                }
            }else {
                response.setStatusCode(404); // Not Found
                response.setMessage("User not found");
            }
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public ReqRes updateUser(User updatedUser, Integer userId) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User existingUser = userOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setFirstName(updatedUser.getFirstName());
                existingUser.setLastName(updatedUser.getLastName());
                existingUser.setDob(updatedUser.getDob());
                existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
                existingUser.setRole(updatedUser.getRole());
                existingUser.setAddress(updatedUser.getAddress());

                // Check if password is present in the request
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    // Encode the password and update it
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                User savedUser = userRepository.save(existingUser);
                reqRes.setUser(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }

    @Override
    public ReqRes getUserById(Integer id) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                reqRes.setUser(user);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User with id '" + id + "' found successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found with id: " + id);
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }

    @Override
    public ReqRes getAllUsers() {
        ReqRes reqRes = new ReqRes();

        try {
            List<User> result = userRepository.findAll();
            if (!result.isEmpty()) {
                reqRes.setUsersList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }

    @Override
    public ReqRes deleteUserById(Integer userId) {
        ReqRes reqRes = new ReqRes();
        try {
            if (userRepository.existsById(userId)) {
                userRepository.deleteById(userId);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User deleted successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }

    @Override
    public ReqRes getMyInfo(String email) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                reqRes.setUser(user);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User info retrieved successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found");
            }

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
        }
        return reqRes;
    }
}
