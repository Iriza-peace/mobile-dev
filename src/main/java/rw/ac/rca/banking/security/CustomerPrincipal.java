package rw.ac.rca.banking.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rw.ac.rca.banking.models.Customer;
import java.util.UUID;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerPrincipal implements UserDetails {
    private UUID id;
    private String email;
    private String firstName;
    private String password;
    private String phone;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomerPrincipal(UUID id, String email, String password, List<GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.password = password;
        this.phone = phone;
        this.authorities = authorities;
    }

    public static CustomerPrincipal create(Customer customer) {
        List<GrantedAuthority> authorities = customer.getProfile().getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());

        return new CustomerPrincipal(
                customer.getCustomer_id(),
                customer.getEmail(),
                customer.getProfile().getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
