package com.banking.exception;

public class BankingException extends RuntimeException{
    protected BankingException(String message) {
        super(message);
    }

    protected BankingException(String message, Throwable cause) {
        super(message, cause);
    }
}
