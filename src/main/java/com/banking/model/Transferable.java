package com.banking.model;

import com.banking.exception.InvalidTransactionException;

public interface Transferable {
    void transfer(Account recipient, double amount);

    default void validateTransfer(Account recipient, double amount) {
        if (recipient == null) {
            throw new InvalidTransactionException("Recipient account cannot be null");
        }
        if (amount <= 0) {
            throw new InvalidTransactionException("Transfer amount must be positive");
        }
    }
}
