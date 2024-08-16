package com.goylik.transactionservice.repository;

import com.goylik.transactionservice.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account a where a.user.userId = :userId")
    List<Account> findByUserId(Long userId);
    Optional<Account> findAccountByAccountNumber(String accountNumber);
}
