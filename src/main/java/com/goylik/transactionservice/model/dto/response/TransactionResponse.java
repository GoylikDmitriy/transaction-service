package com.goylik.transactionservice.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.goylik.transactionservice.model.Currency;
import com.goylik.transactionservice.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse (
        @JsonProperty("transaction_id") Long transactionId,
        @JsonProperty("account_number") String accountNumber,
        @JsonProperty("amount") BigDecimal amount,
        @JsonProperty("currency") @JsonFormat(shape = JsonFormat.Shape.STRING) Currency currency,
        @JsonProperty("transaction_type") TransactionType transactionType,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonProperty("transaction_date")
        LocalDateTime transactionDate
) {}
