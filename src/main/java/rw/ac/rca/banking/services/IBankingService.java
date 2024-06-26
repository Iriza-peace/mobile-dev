package rw.ac.rca.banking.services;

import rw.ac.rca.banking.DTOs.SaveOrWithdrawMoneyDTO;
import rw.ac.rca.banking.models.BankingRecord;
import rw.ac.rca.banking.models.Account;
import rw.ac.rca.banking.DTOs.TransferDTO;

public interface IBankingService {
    public BankingRecord newRecord(SaveOrWithdrawMoneyDTO saveOrWithdrawMoneyDTO) throws Exception;
    public BankingRecord transferMoney(TransferDTO transferMoneyDTO) throws Exception;
}
