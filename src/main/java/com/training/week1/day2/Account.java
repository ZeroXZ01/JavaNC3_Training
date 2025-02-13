package com.training.week1.day2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Base Account class
public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected List<Transaction> transactionHistory;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public abstract void monthlyProcessing();

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    public void deposit(double amount) throws InvalidTransactionException {
        if (amount <= 0) {
            throw new InvalidTransactionException("Deposit amount must be positive");
        }

        balance += amount;
        Transaction transaction = new Transaction(
                TransactionType.DEPOSIT,
                amount,
                "Deposit to " + accountNumber,
                LocalDateTime.now()
        );
        transactionHistory.add(transaction);

        // Add logging
        TransactionLogger.logTransaction(
                accountNumber,
                TransactionType.DEPOSIT,
                amount,
                balance,
                "Deposit processed"
        );
    }

    public void withdraw(double amount) throws InvalidTransactionException {
        if (amount <= 0) {
            throw new InvalidTransactionException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new InvalidTransactionException("Insufficient funds");
        }

        balance -= amount;
        Transaction transaction = new Transaction(
                TransactionType.WITHDRAWAL,
                amount,
                "Withdrawal from " + accountNumber,
                LocalDateTime.now()
        );
        transactionHistory.add(transaction);

        // Add logging
        TransactionLogger.logTransaction(
                accountNumber,
                TransactionType.WITHDRAWAL,
                amount,
                balance,
                "Withdrawal processed"
        );
    }
}
