package com.goylik.transactionservice.model.entity;

import com.goylik.transactionservice.model.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(precision = 17, scale = 2)
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Version
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId.equals(account.accountId) && accountNumber.equals(account.accountNumber) && balance.equals(account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, accountNumber, balance);
    }
}