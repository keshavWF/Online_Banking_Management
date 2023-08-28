package com.banking.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "UserCredential")
public class UserCredential implements UserDetails {
    @Id
    private String userName;

    @Column(columnDefinition = "BINARY(60)")
    private String password;

    @Column
    private String isAdmin;

    @JsonManagedReference
    @OneToMany(mappedBy = "userCredential",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Account> accounts;

    @JsonManagedReference
    @OneToMany(mappedBy = "userCredential",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Payee> payee;

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserCredential() {

    }

    public UserCredential(String userName, String password, String isAdmin) {
        super();
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsAdmin() {

        return isAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Payee> getPayee() {
        return payee;
    }

    public Role getRole() {
        return role;
    }

    public void setIsAdmin(String isAdmin) {

        this.isAdmin = isAdmin;
    }

    public void setAccountList(Account account){
        this.accounts.add(account);
    }

    public void setPayeeList(Payee payee){
        this.payee.add(payee);
    }
    
}
