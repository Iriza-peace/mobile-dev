package rw.ac.rca.banking.DTOs;
import lombok.Data;
@Data
public class AuthenticateDTO {
    private String email;
    private String password;
}