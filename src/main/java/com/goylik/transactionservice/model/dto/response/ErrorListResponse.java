package com.goylik.transactionservice.model.dto.response;

import java.util.List;

public record ErrorListResponse(
        String error,
        int status,
        List<String> messages
) { }
