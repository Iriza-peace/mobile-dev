package rw.ac.rca.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.banking.models.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
}
