package com.banking.service;

import com.banking.exception.InvalidTransactionException;
import com.banking.exception.TransactionFailedException;
import com.banking.model.Account;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionService {
    private final Lock transferLock = new ReentrantLock();

    public void processTransfer(Account fromAccount, Account toAccount, double amount) {
        transferLock.lock();
        try {
            validateTransfer(fromAccount, toAccount, amount);

            // Perform the transfer
            fromAccount.withdraw(amount);
            try {
                toAccount.deposit(amount);
            } catch (Exception e) {
                // Rollback if deposit fails
                fromAccount.deposit(amount);
                throw new TransactionFailedException("Transfer failed, transaction rolled back", e);
            }
        } finally {
            transferLock.unlock();
        }
    }

    private void validateTransfer(Account fromAccount, Account toAccount, double amount) {
        if (fromAccount == null || toAccount == null) {
            throw new InvalidTransactionException("Both accounts must exist");
        }
        if (amount <= 0) {
            throw new InvalidTransactionException("Transfer amount must be positive");
        }
    }
}
