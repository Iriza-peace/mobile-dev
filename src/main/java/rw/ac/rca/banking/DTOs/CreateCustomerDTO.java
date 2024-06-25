package rw.ac.rca.banking.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateCustomerDTO {
  private String firstName;
  private String lastName;
  @Email
  private String email;
  private String mobile;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dob;
  private String password;

}
