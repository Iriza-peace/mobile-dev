package rw.ac.rca.banking.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Customer{
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;
    private double balance;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate lastUpdateTime;
    @OneToMany
    private Set<Account> account = new HashSet<>();
    @OneToOne
    private User profile;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID customer_id;

    public void setCustomer_id(UUID customerId) {
        this.customer_id = customerId;
    }

    public UUID getCustomer_id() {
        return customer_id;
    }
}
