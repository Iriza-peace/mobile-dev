package rw.ac.rca.banking.services;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import rw.ac.rca.banking.DTOs.SaveOrWithdrawMoneyDTO;
import rw.ac.rca.banking.Enumerations.EBankingRecordType;
import rw.ac.rca.banking.events.Transaction;
import rw.ac.rca.banking.mailHandling.EmailHandlerService;
import rw.ac.rca.banking.models.Account;
import rw.ac.rca.banking.models.BankingRecord;
import rw.ac.rca.banking.models.Customer;
import rw.ac.rca.banking.repositories.IAccountRepository;
import rw.ac.rca.banking.repositories.IBankingRepository;
import rw.ac.rca.banking.repositories.ICustomerRepository;
import java.time.LocalDateTime;
import java.util.Objects;
import rw.ac.rca.banking.DTOs.TransferDTO;
import rw.ac.rca.banking.models.Customer;

@Service
@AllArgsConstructor
public class BankingService implements IBankingService{

    private final IBankingRepository bankingRepository;
    private final ICustomerRepository customerRepository;
    private final IAccountRepository accountRepository;
    private final EmailHandlerService emailHandlerService;
    private ApplicationEventPublisher eventPublisher;
    @Override
    public BankingRecord newRecord(SaveOrWithdrawMoneyDTO saveOrWithdrawMoneyDTO) throws Exception {
        try{
            Customer customer = customerRepository.findById(saveOrWithdrawMoneyDTO.getCustomer_id()).orElseThrow(() -> new BadRequestException("User with given id doesn't exist"));
            if(Objects.equals(saveOrWithdrawMoneyDTO.getType(), EBankingRecordType.SAVING.toString())){
                System.out.println("Please wait, saving...");
                customer.setBalance(customer.getBalance() + saveOrWithdrawMoneyDTO.getAmount());
            }else{
                System.out.println("Please wait, withdrawing...");
                if(customer.getBalance() < saveOrWithdrawMoneyDTO.getAmount()){
                    throw new BadRequestException("Amount you have on your balance are insufficient to withdraw, please check your amount");
                }
                customer.setBalance(customer.getBalance() - saveOrWithdrawMoneyDTO.getAmount());
            }
            System.out.println(customer.getBalance());
            customerRepository.save(customer);
            BankingRecord bankingRecord = new BankingRecord();
            bankingRecord.setBankingDateTime(LocalDateTime.now());
            bankingRecord.setType(EBankingRecordType.valueOf(saveOrWithdrawMoneyDTO.getType()));
            Account account = accountRepository.findById(saveOrWithdrawMoneyDTO.getAccount_id()).orElseThrow(() -> new BadRequestException("Account doesn't exist"));
            account.setBalance(customer.getBalance());
            accountRepository.save(account);
            bankingRecord.setAccount(account);
            bankingRecord.setAmount(saveOrWithdrawMoneyDTO.getAmount());
            eventPublisher.publishEvent(new Transaction(customer,bankingRecord));
            return bankingRepository.save(bankingRecord);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
    }}


    @PostMapping("/transfer")
    @Override
    public BankingRecord transferMoney(TransferDTO transferMoneyDTO) throws Exception {
        try {
            Account sourceAccount = accountRepository.findById(transferMoneyDTO.getSourceAccountId())
                    .orElseThrow(() -> new BadRequestException("Source account doesn't exist"));
            Account targetAccount = accountRepository.findById(transferMoneyDTO.getTargetAccountId())
                    .orElseThrow(() -> new BadRequestException("Target account doesn't exist"));

            System.out.println(sourceAccount.getBalance());
                if (sourceAccount.getBalance() < transferMoneyDTO.getAmount()) {
                    throw new BadRequestException("Insufficient balance in source account");
                }
                sourceAccount.setBalance(sourceAccount.getBalance() - transferMoneyDTO.getAmount());
                targetAccount.setBalance(targetAccount.getBalance() + transferMoneyDTO.getAmount());

            accountRepository.save(sourceAccount);
            accountRepository.save(targetAccount);

            BankingRecord bankingRecord = new BankingRecord();
            bankingRecord.setBankingDateTime(LocalDateTime.now());
            bankingRecord.setType(EBankingRecordType.valueOf(transferMoneyDTO.getType()));
            bankingRecord.setAmount(transferMoneyDTO.getAmount());
            bankingRecord.setAccount(sourceAccount);
            return bankingRepository.save(bankingRecord);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
