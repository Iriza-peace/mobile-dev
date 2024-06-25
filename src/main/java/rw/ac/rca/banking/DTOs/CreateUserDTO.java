package rw.ac.rca.banking.DTOs;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String firstName;
    private String email;
    private String password;
    private String phone;

}
