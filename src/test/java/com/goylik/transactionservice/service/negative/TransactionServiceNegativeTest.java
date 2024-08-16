package com.goylik.transactionservice.service.negative;

import com.goylik.transactionservice.model.Currency;
import com.goylik.transactionservice.model.dto.request.DepositRequest;
import com.goylik.transactionservice.service.config.TransactionServiceTestConfig;
import com.goylik.transactionservice.service.exception.CurrenciesMismatchedException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TransactionServiceNegativeTest extends TransactionServiceTestConfig {
    @Test
    public void testDepositThrowCurrencyMismatchedException() {
        var badRequest = new DepositRequest(
                account.getAccountNumber(),
                new BigDecimal("131"),
                Currency.USD
        );

        when(accountRepository.findAccountByAccountNumber(account.getAccountNumber())).thenReturn(Optional.of(account));

        assertThrows(CurrenciesMismatchedException.class, () -> transactionService.deposit(badRequest));

        verify(accountRepository, times(1)).findAccountByAccountNumber(account.getAccountNumber());
        verify(transactionRepository, times(0)).save(transaction);
    }
}
