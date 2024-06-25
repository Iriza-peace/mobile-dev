package rw.ac.rca.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.banking.models.Account;

import java.util.UUID;

public interface IAccountRepository extends JpaRepository<Account, UUID> {
}
