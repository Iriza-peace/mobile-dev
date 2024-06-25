package rw.ac.rca.banking.services.ServicesImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.banking.models.Customer;
import rw.ac.rca.banking.repositories.ICustomerRepository;
import rw.ac.rca.banking.services.ICustomerService;

@AllArgsConstructor
@Service
public class CustomerService implements ICustomerService {
    private final ICustomerRepository customerRepository;
    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
