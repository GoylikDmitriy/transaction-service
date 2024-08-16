package com.goylik.transactionservice.util.mapper;

import com.goylik.transactionservice.model.TransactionType;
import com.goylik.transactionservice.model.dto.request.DepositRequest;
import com.goylik.transactionservice.model.dto.response.TransactionResponse;
import com.goylik.transactionservice.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.LocalDateTime;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        imports = { LocalDateTime.class })
public interface TransactionMapper {
    TransactionResponse mapToTransactionResponse(Transaction transaction);

    @Mapping(target = "transactionDate", expression = "java(LocalDateTime.now())")
    Transaction mapToTransaction(TransactionType transactionType, DepositRequest request);
}
