package com.mysecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private UserData customer;

    private String account;
    private double amount;
    private String type;

    private LocalDateTime bankingDateTime;
    // existing getters and setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserData getCustomer() {
        return customer;
    }

    public void setCustomer(UserData customer) {
        this.customer = customer;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getBankingDateTime() {
        return bankingDateTime;
    }

    public void setBankingDateTime(LocalDateTime bankingDateTime) {
        this.bankingDateTime = bankingDateTime;
    }

    public double getBalance() {
        double balance = 0;
        return balance;
    }
    public void setBalance(double newBalance) {
        double balance = newBalance;
        return;


    }
}



