package com.goylik.transactionservice.controller.config;

import com.goylik.transactionservice.controller.TransactionController;
import com.goylik.transactionservice.model.Currency;
import com.goylik.transactionservice.model.TransactionType;
import com.goylik.transactionservice.model.dto.request.DepositRequest;
import com.goylik.transactionservice.model.dto.response.TransactionResponse;
import com.goylik.transactionservice.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@WebMvcTest(TransactionController.class)
public abstract class TransactionControllerTestConfig {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected MockMvc mockMvc;
    @MockBean
    protected TransactionService transactionService;

    protected TransactionResponse response;
    protected DepositRequest request;

    @BeforeEach
    public void setup() {
        String accountNumber = "8751248A0BP4128D1";
        BigDecimal amount = new BigDecimal("141.21");
        Currency currency = Currency.BYN;

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
