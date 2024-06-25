package rw.ac.rca.banking.DTOs;

import lombok.Data;

import java.util.UUID;

@Data
public class SaveOrWithdrawMoneyDTO {
    private UUID account_id;
    private UUID customer_id;
    private double amount;
    private String type;

}
