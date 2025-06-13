package com.banking.BankingApp.entity;

import com.banking.BankingApp.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String email;

    private String fullName;

    @NotNull
    @Column(unique = true)
    private Long phoneNumber;

    @Size(min = 8)
    private String password;

    @Column(unique = true)
    private Long accountNumber;


    private BigDecimal accountBalance;

    @CreationTimestamp
    private Date createdAt;
    @NotNull
    private String address;
    @Enumerated(EnumType.STRING)
    private Roles role;

    private Boolean enabled;







    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
