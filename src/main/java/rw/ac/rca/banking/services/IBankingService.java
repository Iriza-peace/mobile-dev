package rw.ac.rca.banking.services;

import rw.ac.rca.banking.DTOs.SaveOrWithdrawMoneyDTO;
import rw.ac.rca.banking.models.BankingRecord;

public interface IBankingService {
    public BankingRecord newRecord(SaveOrWithdrawMoneyDTO saveOrWithdrawMoneyDTO) throws Exception;
}
