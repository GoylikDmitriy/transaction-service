package com.goylik.transactionservice.model.entity;

import com.goylik.transactionservice.model.Currency;
import com.goylik.transactionservice.model.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(precision = 17, scale = 2)
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(name = "transaction_type")
    private TransactionType transactionType;
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionId.equals(that.transactionId) && amount.equals(that.amount) &&
                transactionType == that.transactionType &&
                transactionDate.equals(that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, amount, transactionType, transactionDate);
    }
}