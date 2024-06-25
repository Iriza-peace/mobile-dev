package rw.ac.rca.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rw.ac.rca.banking.Enumerations.ERole;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer user_id;
    @Column(unique = true)
    private String email;
    private String password;
    private Set<ERole> Roles;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
    public void setId(Long id) {
        this.user_id = Math.toIntExact(id);
    }

    public Integer getId() {
        return user_id;
    }
}
