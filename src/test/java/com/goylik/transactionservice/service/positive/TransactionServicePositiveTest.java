package com.goylik.transactionservice.service.positive;

import com.goylik.transactionservice.model.TransactionType;
import com.goylik.transactionservice.service.config.TransactionServiceTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServicePositiveTest extends TransactionServiceTestConfig {
    @Test
    public void testDepositPositive() {
        var transactionType = TransactionType.DEPOSIT;
        when(accountRepository.findAccountByAccountNumber(account.getAccountNumber())).thenReturn(Optional.of(account));
        when(transactionMapper.mapToTransaction(transactionType, request)).thenReturn(transaction);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(transactionMapper.mapToTransactionResponse(transaction)).thenReturn(response);

        var actualResponse = transactionService.deposit(request);

        verify(accountRepository, times(1)).findAccountByAccountNumber(account.getAccountNumber());
        verify(transactionRepository, times(1)).save(transaction);

        assertEquals(actualResponse, response);
    }

    @Test
    public void testCreateTransaction() {
        var transactionType = TransactionType.DEPOSIT;
        when(transactionMapper.mapToTransaction(transactionType, request)).thenReturn(transaction);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(transactionMapper.mapToTransactionResponse(transaction)).thenReturn(response);

        transactionService.createTransaction(transactionType, request);

        verify(transactionMapper, times(1)).mapToTransaction(transactionType, request);
        verify(transactionRepository, times(1)).save(transaction);
        verify(transactionMapper, times(1)).mapToTransactionResponse(transaction);
    }

    @Test
    public void testGetTransactionById() {
        Long transactionId = 1L;
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));

        transactionService.getTransactionById(transactionId);

        verify(transactionRepository, times(1)).findById(transactionId);
        verify(transactionMapper, times(1)).mapToTransactionResponse(transaction);
    }
}
