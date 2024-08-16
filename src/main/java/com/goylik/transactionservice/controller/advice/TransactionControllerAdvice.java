package com.goylik.transactionservice.controller.advice;

import com.goylik.transactionservice.model.dto.response.ErrorResponse;
import com.goylik.transactionservice.model.dto.response.ErrorListResponse;
import com.goylik.transactionservice.service.exception.AccountNotFoundException;
import com.goylik.transactionservice.service.exception.CurrenciesMismatchedException;
import com.goylik.transactionservice.service.exception.TransactionNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class TransactionControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorListResponse handleValidationException(MethodArgumentNotValidException ex) {
        log.error("VALIDATION ERROR: {}", ex.getMessage(), ex);

        List<String> validationErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return new ErrorListResponse("Validation Error", 400, validationErrors);
    }

    @ExceptionHandler(CurrenciesMismatchedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCurrenciesMismatchedException(CurrenciesMismatchedException ex) {
        log.error("BAD REQUEST: {}", ex.getMessage(), ex);
        return new ErrorResponse("Bad Request", 400, ex.getMessage());
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleOptimisticLockingFailureException(OptimisticLockingFailureException ex) {
        log.error("CONFLICT: {}", ex.getMessage(), ex);
        return new ErrorResponse("Conflict", 409, "Simultaneous data access: " + ex.getMessage());
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleAccountNotFoundException(AccountNotFoundException ex) {
        log.error("NOT FOUND: {}", ex.getMessage(), ex);
        return new ErrorResponse("Not Found", 404, ex.getMessage());
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleTransactionNotFoundException(TransactionNotFoundException ex) {
        log.error("NOT FOUND: {}", ex.getMessage(), ex);
        return new ErrorResponse("Not Found", 404, ex.getMessage());
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleDataAccessException(DataAccessException ex) {
        log.error("DATA ACCESS ERROR: {}", ex.getMessage(), ex);
        return new ErrorResponse("Internal Server Error", 500,
                "An error occurred while accessing the data: " + ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleRuntimeException(RuntimeException ex) {
        log.error("INTERNAL SERVER ERROR: {}", ex.getMessage(), ex);
        return new ErrorResponse("Internal Server Error", 500, ex.getMessage());
    }
}
