package rw.ac.rca.banking.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.banking.DTOs.CreateUserDTO;
import rw.ac.rca.banking.Enumerations.ERole;
import rw.ac.rca.banking.Utils.ApiResponse;
import rw.ac.rca.banking.models.Customer;
import rw.ac.rca.banking.models.User;
import rw.ac.rca.banking.services.ICustomerService;
import rw.ac.rca.banking.services.IUserService;

import java.util.Collections;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private ModelMapper modelMapper;
    private IUserService userService;
    private ICustomerService customerService;
    private PasswordEncoder passwordEncoder;
    @GetMapping()
    public String sayHello(){
        return "Hello, this endpoint is for bank management system";
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody CreateUserDTO userDTO){
        Customer newUser = new Customer();
        modelMapper.map(userDTO,newUser);
        User user = new User(userDTO.getEmail(),passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(Collections.singleton(ERole.CUSTOMER));
        user = userService.createUser(user);
        newUser.setProfile(user);
        newUser = customerService.createCustomer(newUser);
        return ResponseEntity.ok(new ApiResponse("Created customer successfully", HttpStatus.CREATED,newUser));
    }
}
