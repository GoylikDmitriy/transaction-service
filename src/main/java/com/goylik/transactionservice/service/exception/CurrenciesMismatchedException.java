package com.goylik.transactionservice.service.exception;

public class CurrenciesMismatchedException extends RuntimeException {
    public CurrenciesMismatchedException() {
    }

    public CurrenciesMismatchedException(String message) {
        super(message);
    }

    public CurrenciesMismatchedException(String message, Throwable cause) {
        super(message, cause);
    }
}
