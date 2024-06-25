package rw.ac.rca.banking.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rw.ac.rca.banking.DTOs.CreateCustomerDTO;
import rw.ac.rca.banking.DTOs.SaveOrWithdrawMoneyDTO;
import rw.ac.rca.banking.Enumerations.ERole;
import rw.ac.rca.banking.Utils.ApiResponse;
import rw.ac.rca.banking.models.Account;
import rw.ac.rca.banking.models.BankingRecord;
import rw.ac.rca.banking.models.Customer;
import rw.ac.rca.banking.models.User;
import rw.ac.rca.banking.repositories.IAccountRepository;
import rw.ac.rca.banking.repositories.IUserRepository;
import rw.ac.rca.banking.services.IBankingService;
import rw.ac.rca.banking.services.ICustomerService;
import rw.ac.rca.banking.services.IUserService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final ICustomerService customerService;
    private final IUserService userService;
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final IAccountRepository accountRepository;
    private final IBankingService bankingService;
    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody CreateCustomerDTO dto){
        Customer newCustomer = new Customer();
        modelMapper.map(dto,newCustomer);
        newCustomer.setLastUpdateTime(LocalDate.now());
        newCustomer.setBalance(0.0);

        User user = new User(dto.getEmail(),passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Collections.singleton(ERole.CUSTOMER));
        user = userService.createUser(user);
        newCustomer.setProfile(user);
        Account account = new Account();
        account.setNumber("0790172051" + accountRepository.findAll().size() + 1);
        account = accountRepository.save(account);
        Set<Account> accounts = newCustomer.getAccount();
        accounts.add(account);
        newCustomer.setAccount(accounts);
        newCustomer = customerService.createCustomer(newCustomer);
        return ResponseEntity.ok(new ApiResponse<Customer>("Successfully a customer is created", HttpStatus.CREATED,newCustomer));
    }

    @PostMapping("/transaction")
    public ResponseEntity<?> saveMoney(@RequestBody SaveOrWithdrawMoneyDTO dto) throws Exception {
        BankingRecord record = bankingService.newRecord(dto);
        return ResponseEntity.ok(new ApiResponse<>("Your transaction has 0completed successfully!",HttpStatus.CREATED,record));
    }
}
