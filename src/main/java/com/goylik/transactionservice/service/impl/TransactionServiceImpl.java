package com.goylik.transactionservice.service.impl;

import com.goylik.transactionservice.model.Currency;
import com.goylik.transactionservice.model.TransactionType;
import com.goylik.transactionservice.model.dto.request.DepositRequest;
import com.goylik.transactionservice.model.dto.response.TransactionResponse;
import com.goylik.transactionservice.model.entity.Account;
import com.goylik.transactionservice.repository.AccountRepository;
import com.goylik.transactionservice.repository.TransactionRepository;
import com.goylik.transactionservice.service.TransactionService;
import com.goylik.transactionservice.service.exception.AccountNotFoundException;
import com.goylik.transactionservice.service.exception.CurrenciesMismatchedException;
import com.goylik.transactionservice.service.exception.TransactionNotFoundException;
import com.goylik.transactionservice.util.mapper.TransactionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;

    @Override
    @Transactional
    public TransactionResponse deposit(DepositRequest request) {
        var account = fetchAccountByAccountNumber(request.accountNumber());
        addToBalance(account, request);
        return createTransaction(TransactionType.DEPOSIT, request);
    }

    @Override
    @Transactional
    public TransactionResponse createTransaction(TransactionType transactionType, DepositRequest request) {
        var transaction = transactionMapper.mapToTransaction(transactionType, request);
        transaction = transactionRepository.save(transaction);
        return transactionMapper.mapToTransactionResponse(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionResponse getTransactionById(Long transactionId) {
        var transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found."));

        return transactionMapper.mapToTransactionResponse(transaction);
    }

    private void addToBalance(Account account, DepositRequest request) {
        if (!isCurrenciesMatch(account, request.currency())) {
            throw new CurrenciesMismatchedException(
                    String.format("Transaction currency (%s) doesn't match account currency (%s).",
                            request.currency(),
                            account.getCurrency())
            );
        }

        account.setBalance(account.getBalance().add(request.amount()));
    }

    private boolean isCurrenciesMatch(Account account, Currency transactionCurrency) {
        return account.getCurrency().equals(transactionCurrency);
    }

    private Account fetchAccountByAccountNumber(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found."));
    }
}