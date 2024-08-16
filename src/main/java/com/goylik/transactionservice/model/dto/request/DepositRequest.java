package com.goylik.transactionservice.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.goylik.transactionservice.model.Currency;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DepositRequest(
        @NotBlank
        @JsonProperty("account_number") String accountNumber,
        @NotNull
        @DecimalMin(value = "0.01")
        @Digits(integer = 17, fraction = 2)
        @JsonProperty("amount") BigDecimal amount,
        @NotNull
        @JsonProperty("currency") @JsonFormat(shape = JsonFormat.Shape.STRING) Currency currency
) {}
