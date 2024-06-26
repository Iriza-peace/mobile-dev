package rw.ac.rca.banking.listeners;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import rw.ac.rca.banking.mailHandling.EmailHandlerService;
import rw.ac.rca.banking.models.BankingRecord;
import rw.ac.rca.banking.models.Customer;
import rw.ac.rca.banking.models.Message;
import rw.ac.rca.banking.repositories.IMessageRepository;
import java.time.LocalDateTime;
//The Transaction class listens to the Transaction event and sends an email to the customer
@Component
@AllArgsConstructor
public class Transaction implements ApplicationListener<rw.ac.rca.banking.events.Transaction> {
    private final EmailHandlerService emailHandlerService;
    private final IMessageRepository messageRepository;
    @Override
    public void onApplicationEvent(rw.ac.rca.banking.events.Transaction event) {
        Customer customer = (Customer) event.getSource();
        BankingRecord bankingRecord = event.getBankingRecord();
        String sentMessage = "Dear" + customer.getFirstName() + " " + customer.getLastName() + " Your " + bankingRecord.getType().toString() + " of " + bankingRecord.getAmount() + " RWF on your " + bankingRecord.getAccount().getNumber() + " has been successfully completed!";
        emailHandlerService.sendMessage(customer.getEmail(), "Transaction Successful!", sentMessage);
        Message message = new Message();
        message.setCustomer(customer);
        message.setMessage(sentMessage);
        message.setDateTime(LocalDateTime.now());

        try {
            messageRepository.save(message);
        } catch (DuplicateKeyException e) {
            System.out.println("Duplicate entry error: " + e.getMessage());

        }
    }
}
