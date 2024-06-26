//package rw.ac.rca.banking.DTOs;
//
//import java.util.UUID;
//
//public class TransferDTO {
//    private UUID sourceAccountId;
//    private UUID targetAccountId;
//    private double amount;
//
//
//    public TransferDTO(UUID sourceAccountId, UUID targetAccountId, double amount) {
//        this.sourceAccountId = sourceAccountId;
//        this.targetAccountId = targetAccountId;
//        this.amount = amount;
//    }
//
//    public UUID getSourceAccountId() {
//        return sourceAccountId;
//    }
//
//    public void setSourceAccountId(UUID sourceAccountId) {
//        this.sourceAccountId = sourceAccountId;
//    }
//
//    public UUID getTargetAccountId() {
//        return targetAccountId;
//    }
//
//    public void setTargetAccountId(UUID targetAccountId) {
//        this.targetAccountId = targetAccountId;
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//
//    @Override
//    public String toString() {
//        return "TransferDTO{" +
//                "sourceAccountId=" + sourceAccountId +
//                ", targetAccountId=" + targetAccountId +
//                ", amount=" + amount +
//                '}';
//    }
//
//    public String getType() {
//
//    }
//}


package rw.ac.rca.banking.DTOs;

import lombok.Data;

import java.util.UUID;

@Data
public class TransferDTO {

    private UUID sourceAccountId;
    private UUID targetAccountId;
    private double amount;
    private String type;


}
