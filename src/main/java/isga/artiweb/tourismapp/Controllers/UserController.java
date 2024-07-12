package isga.artiweb.tourismapp.Controllers;

import isga.artiweb.tourismapp.dto.ApiResponse;
import isga.artiweb.tourismapp.dto.UserDTO;
import isga.artiweb.tourismapp.dto.UserLoginDTO;
import isga.artiweb.tourismapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userdto) {
        UserDTO createdUser = this.userService.createUser(userdto);
        return new ResponseEntity<UserDTO>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/signIn")
    public ResponseEntity<UserDTO> authenticateCustomer(@RequestBody UserLoginDTO userdto)
    {
        UserDTO getUserByEmail = this.userService.getUserByEmail(userdto.getEmail());
        return ResponseEntity.ok(getUserByEmail);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userdto, @PathVariable Integer userId) {
        UserDTO updateUser = this.userService.updateUser(userdto, userId);
        return new ResponseEntity<UserDTO>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
        this.userService.deleteUserById(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User is deleted sucessfully", true), HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) {
        UserDTO getUser = this.userService.getUserById(userId);
        return new ResponseEntity<UserDTO>(getUser, HttpStatus.OK);
    }

    // for checking purpose only
    @GetMapping("/getall")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> getUsers = this.userService.getAllUsers();
        return new ResponseEntity<List<UserDTO>>(getUsers, HttpStatus.OK);
    }
}
