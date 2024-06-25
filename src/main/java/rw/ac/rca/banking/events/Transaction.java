package rw.ac.rca.banking.events;

import lombok.Data;
import org.springframework.context.ApplicationEvent;
import rw.ac.rca.banking.models.BankingRecord;

//This class is used to create an event that will be triggered when a transaction is made
@Data
public class Transaction extends ApplicationEvent {
    private final BankingRecord bankingRecord;
    public Transaction(Object source, BankingRecord record) {
        super(source);
        bankingRecord = record;
    }
}
