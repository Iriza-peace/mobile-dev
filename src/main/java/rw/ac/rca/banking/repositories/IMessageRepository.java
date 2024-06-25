package rw.ac.rca.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.banking.models.Message;

import java.util.UUID;

public interface IMessageRepository extends JpaRepository<Message, UUID> {
}
