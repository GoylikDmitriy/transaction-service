package com.goylik.transactionservice.service.config;

import com.goylik.transactionservice.model.Currency;
import com.goylik.transactionservice.model.DocumentType;
import com.goylik.transactionservice.model.TransactionType;
import com.goylik.transactionservice.model.dto.request.DepositRequest;
import com.goylik.transactionservice.model.dto.response.TransactionResponse;
import com.goylik.transactionservice.model.entity.Account;
import com.goylik.transactionservice.model.entity.Transaction;
import com.goylik.transactionservice.model.entity.User;
import com.goylik.transactionservice.repository.AccountRepository;
import com.goylik.transactionservice.repository.TransactionRepository;
import com.goylik.transactionservice.service.impl.TransactionServiceImpl;
import com.goylik.transactionservice.util.mapper.TransactionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public abstract class TransactionServiceTestConfig {
    @InjectMocks
    protected TransactionServiceImpl transactionService;
    @Mock
    protected AccountRepository accountRepository;
    @Mock
    protected TransactionRepository transactionRepository;
    @Mock
    protected TransactionMapper transactionMapper;

    protected User user;
    protected Account account;
    protected Transaction transaction;
    protected TransactionResponse response;
    protected DepositRequest request;

    @BeforeEach
    public void setup() {
        String accountNumber = "8751248A0BP4128D1";
        BigDecimal amount = new BigDecimal("141.21");
        Currency currency = Currency.BYN;

        user = new User();
        user.setUserId(1L);
        user.setName("Alex");
        user.setDocumentNumber("7841298471824A90");
        user.setDocumentType(DocumentType.PASSPORT);

        account = new Account();
        account.setAccountId(1L);
        account.setAccountNumber(accountNumber);
        account.setBalance(new BigDecimal("100"));
        account.setCurrency(currency);
        account.setUser(user);

        user.setAccounts(List.of(account));

        transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setCurrency(currency);
        transaction.setTransactionDate(LocalDateTime.MAX);

        response = new TransactionResponse(
                1L,
                accountNumber,
                amount,
                currency,
                TransactionType.DEPOSIT,
                LocalDateTime.MAX
        );

        request = new DepositRequest(
                accountNumber,
                amount,
                currency
        );
    }
}
