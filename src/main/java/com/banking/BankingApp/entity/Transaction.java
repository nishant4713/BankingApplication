package com.banking.BankingApp.entity;

import com.banking.BankingApp.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long trans_id;
    @CreationTimestamp
    LocalDateTime time;

    double amount;

    @ManyToOne
    private Account account;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
}
