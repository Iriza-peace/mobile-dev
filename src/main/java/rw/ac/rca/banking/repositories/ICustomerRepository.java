package rw.ac.rca.banking.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.banking.models.Customer;
import java.util.Optional;
import java.util.UUID;

public interface ICustomerRepository extends JpaRepository<Customer, UUID> {
    public Optional<Customer> findByEmail(String email);
}
