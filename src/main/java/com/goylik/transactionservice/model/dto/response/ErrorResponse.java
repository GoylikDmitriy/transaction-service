package com.goylik.transactionservice.model.dto.response;

public record ErrorResponse (
        String error,
        int status,
        String message
) {}
