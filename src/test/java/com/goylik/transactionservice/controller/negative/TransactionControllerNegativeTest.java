package com.goylik.transactionservice.controller.negative;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goylik.transactionservice.controller.config.TransactionControllerTestConfig;
import com.goylik.transactionservice.model.dto.request.DepositRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransactionControllerNegativeTest extends TransactionControllerTestConfig {
    @Test
    public void testDepositRequestBodyIsNotValid() throws Exception {
        var badRequest = new DepositRequest(
                "8751248A0BP4128D1",
                new BigDecimal("-12"),
                null
        );

        mockMvc.perform(post("/api/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(badRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", equalTo("Validation Error")))
                .andExpect(jsonPath("$.status", equalTo(400)))
                .andExpect(jsonPath("$.messages", hasSize(2)))
                .andReturn();

        verify(transactionService, times(0)).deposit(badRequest);
    }
}
