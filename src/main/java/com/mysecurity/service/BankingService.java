package com.mysecurity.service;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mysecurity.entity.Banking;
import com.mysecurity.enums.BankingEnum;
import com.mysecurity.repository.BankingRepository;

import java.util.Optional;

@Service

public class BankingService {
    @Autowired
    private BankingRepository repository;

    public Banking addTransaction(Banking banking) {
        return repository.save(banking);
    }

    @Enumerated (EnumType.STRING)
    private BankingEnum type;
    public Banking withdraw(int id, double amount) {
        Optional<Banking> existingAccount = repository.findById(id);
        if (existingAccount.isPresent()) {
            Banking accountToUpdate = existingAccount.get();
            double currentBalance = accountToUpdate.getBalance();
            if (currentBalance < amount) {
                throw new RuntimeException("Insufficient balance for withdrawal");
            }
            double newBalance = currentBalance - amount;
            accountToUpdate.setBalance(newBalance);
            accountToUpdate.setType(String.valueOf(BankingEnum.WITHDRAW));
            repository.save(accountToUpdate);
            return accountToUpdate;
        } else {
            throw new RuntimeException("Account with id " + id + " not found");
        }
    }
    public Banking deposit(int id, double amount) {
        Optional<Banking> existingAccount = repository.findById(id);
        if (existingAccount.isPresent()) {
            Banking accountToUpdate = existingAccount.get();
            double currentBalance = accountToUpdate.getBalance();
            double newBalance = currentBalance + amount;
            accountToUpdate.setBalance(newBalance);
            accountToUpdate.setType(String.valueOf(BankingEnum.SAVING));
            repository.save(accountToUpdate);
            return accountToUpdate;
        } else {
            throw new RuntimeException("Account with id " + id + " not found");
        }
    }
        public Banking transfer(int sourceId, int destinationId, double amount) {
            Optional<Banking> existingSourceAccount = repository.findById(sourceId);
            Optional<Banking> existingDestinationAccount = repository.findById(destinationId);
            if (existingSourceAccount.isPresent() && existingDestinationAccount.isPresent()) {
                Banking sourceAccountToUpdate = existingSourceAccount.get();
                Banking destinationAccountToUpdate = existingDestinationAccount.get();
                double sourceCurrentBalance = sourceAccountToUpdate.getBalance();
                if (sourceCurrentBalance < amount) {
                    throw new RuntimeException("Insufficient balance for transfer");
                }
                double sourceNewBalance = sourceCurrentBalance - amount;
                double destinationNewBalance = destinationAccountToUpdate.getBalance() + amount;


                sourceAccountToUpdate.setBalance(sourceNewBalance);
                destinationAccountToUpdate.setBalance(destinationNewBalance);
                sourceAccountToUpdate.setType(String.valueOf(BankingEnum.TRANSFER));
                destinationAccountToUpdate.setType(String.valueOf(BankingEnum.TRANSFER));
                repository.save(sourceAccountToUpdate);
                repository.save(destinationAccountToUpdate);
                return sourceAccountToUpdate;
            } else {
                throw new RuntimeException("Account with id " + sourceId + " or " + destinationId + " not found");

            }
        }


    public Banking updateTransaction(int id, Banking banking) {
        Optional<Banking> existingTransaction = repository.findById(id);
        if (existingTransaction.isPresent()) {
            Banking transactionToUpdate = existingTransaction.get();
            transactionToUpdate.setCustomer(banking.getCustomer());
            transactionToUpdate.setAccount(banking.getAccount());
            transactionToUpdate.setAmount(banking.getAmount());
            transactionToUpdate.setType(banking.getType());
            transactionToUpdate.setBankingDateTime(banking.getBankingDateTime());
            repository.save(transactionToUpdate);
            return transactionToUpdate;
        } else {
            throw new RuntimeException("Transaction with id " + id + " not found");
        }
    }
}