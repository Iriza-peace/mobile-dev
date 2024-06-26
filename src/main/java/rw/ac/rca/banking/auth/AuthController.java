package rw.ac.rca.banking.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rw.ac.rca.banking.DTOs.AuthenticateDTO;
import rw.ac.rca.banking.Utils.ApiResponse;
import rw.ac.rca.banking.models.Customer;
import rw.ac.rca.banking.repositories.ICustomerRepository;
import rw.ac.rca.banking.security.CustomerPrincipal;
import rw.ac.rca.banking.services.JwtService;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticatedManager;
    private final PasswordEncoder passwordEncoder;
    private final ICustomerRepository customerRepository;
    private final JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> authenticate(@RequestBody AuthenticateDTO authenticateDTO){
       Customer customer = customerRepository.findByEmail(authenticateDTO.getEmail()).orElseThrow(() -> new BadCredentialsException("Customer with given email is not found!"));
        if (customer == null || !passwordEncoder.matches(authenticateDTO.getPassword(), customer.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }
        authenticatedManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateDTO.getEmail(),authenticateDTO.getPassword()));
       String jwtToken = jwtService.generateToken(CustomerPrincipal.create(customer));
       return ResponseEntity.ok(new ApiResponse<>("Login success", HttpStatus.ACCEPTED,jwtToken));
    }
}
