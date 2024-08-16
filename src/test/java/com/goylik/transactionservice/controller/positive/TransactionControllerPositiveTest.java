package com.goylik.transactionservice.controller.positive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goylik.transactionservice.controller.config.TransactionControllerTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TransactionControllerPositiveTest extends TransactionControllerTestConfig {
    @Test
    public void testDeposit() throws Exception {
        String expectedDate = response.transactionDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        when(transactionService.deposit(request)).thenReturn(response);

        mockMvc.perform(post("/api/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transaction_id", equalTo((response.transactionId().intValue()))))
                .andExpect(jsonPath("$.account_number", equalTo(response.accountNumber())))
                .andExpect(jsonPath("$.amount", equalTo(response.amount().doubleValue())))
                .andExpect(jsonPath("$.currency", equalTo(response.currency().name())))
                .andExpect(jsonPath("$.transaction_type", equalTo(response.transactionType().name())))
                .andExpect(jsonPath("$.transaction_date", equalTo(expectedDate)));
    }
}
