package rw.ac.rca.banking.listeners;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import rw.ac.rca.banking.events.TransactionCompleteEvent;
import rw.ac.rca.banking.mailHandling.EmailHandlerService;
import rw.ac.rca.banking.models.BankingRecord;
import rw.ac.rca.banking.models.Customer;
import rw.ac.rca.banking.models.Message;
import rw.ac.rca.banking.repositories.IMessageRepository;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class TransactionCompleteListener implements ApplicationListener<TransactionCompleteEvent> {
    private final EmailHandlerService emailHandlerService;
    private final IMessageRepository messageRepository;
    @Override
    public void onApplicationEvent(TransactionCompleteEvent event) {
        Customer customer = (Customer) event.getSource();
        BankingRecord bankingRecord = event.getBankingRecord();
        String sentMessage = "Dear " + customer.getFirstName() + " " + customer.getLastName() + " Your " + bankingRecord.getType().toString() + " of " + bankingRecord.getAmount() + " RWF on your " + bankingRecord.getAccount().getNumber() + " has been successfully completed!";
        emailHandlerService.sendMessage(customer.getEmail(), "Transaction Successful!",sentMessage);
        Message message = new Message();
        message.setCustomer(customer);
        message.setMessage(sentMessage);
        message.setDateTime(LocalDateTime.now());
        messageRepository.save(message);
    }
}
