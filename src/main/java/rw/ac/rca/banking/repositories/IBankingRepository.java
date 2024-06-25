package rw.ac.rca.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.banking.models.BankingRecord;

import java.util.UUID;

public interface IBankingRepository extends JpaRepository<BankingRecord, UUID> {
}
