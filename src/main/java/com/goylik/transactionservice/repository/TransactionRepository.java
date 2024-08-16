package com.goylik.transactionservice.repository;

import com.goylik.transactionservice.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
