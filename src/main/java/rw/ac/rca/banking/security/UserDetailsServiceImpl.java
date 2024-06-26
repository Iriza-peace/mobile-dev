package rw.ac.rca.banking.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rw.ac.rca.banking.models.Customer;
import rw.ac.rca.banking.repositories.ICustomerRepository;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final ICustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer =  customerRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return CustomerPrincipal.create(customer);
    }
}