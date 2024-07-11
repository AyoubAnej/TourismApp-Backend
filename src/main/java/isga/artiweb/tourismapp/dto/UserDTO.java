package isga.artiweb.tourismapp.dto;

import isga.artiweb.tourismapp.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserDTO {
    private Integer userId;
    private String email;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String address;
    private Long phoneNo;
}
