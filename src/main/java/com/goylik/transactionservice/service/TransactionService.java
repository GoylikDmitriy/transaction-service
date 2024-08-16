package com.goylik.transactionservice.service;

import com.goylik.transactionservice.model.TransactionType;
import com.goylik.transactionservice.model.dto.request.DepositRequest;
import com.goylik.transactionservice.model.dto.response.TransactionResponse;

public interface TransactionService {
    TransactionResponse deposit(DepositRequest request);
    TransactionResponse createTransaction(TransactionType transactionType, DepositRequest request);
    TransactionResponse getTransactionById(Long transactionId);
}
