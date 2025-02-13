package com.banking.exception;

public class TransactionLimitException extends BankingException{
    public TransactionLimitException(String message) {
        super(message);
    }
}
