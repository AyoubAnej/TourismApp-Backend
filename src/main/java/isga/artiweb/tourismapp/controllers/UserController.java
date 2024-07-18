package isga.artiweb.tourismapp.controllers;

import isga.artiweb.tourismapp.dto.ReqRes;
import isga.artiweb.tourismapp.dto.UserDTO;
import isga.artiweb.tourismapp.dto.UserLoginDTO;
import isga.artiweb.tourismapp.entities.User;
import isga.artiweb.tourismapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> createuser(@RequestBody ReqRes req){
        return ResponseEntity.ok(userService.register(req));
    }
    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(userService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(userService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());

    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<ReqRes> getUSerByID(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.getUserById(userId));

    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody User reqres){
        return ResponseEntity.ok(userService.updateUser(reqres, userId));
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUser(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }


//    @PostMapping("/signIn")
//    public ResponseEntity<UserDTO> authenticateCustomer(@RequestBody UserLoginDTO userLoginDTO){
//        UserDTO
//    }
}
