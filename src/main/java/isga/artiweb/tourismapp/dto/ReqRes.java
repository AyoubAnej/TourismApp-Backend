package isga.artiweb.tourismapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import isga.artiweb.tourismapp.entities.Role;
import isga.artiweb.tourismapp.entities.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {
    private int statusCode;
    private Integer id;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String email;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String address;
    private Long phoneNo;
    private User user;
    private List<User> usersList;
}
