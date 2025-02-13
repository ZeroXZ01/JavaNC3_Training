package com.banking.model;

import com.banking.exception.InsufficientFundsException;
import com.banking.util.TransactionLogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account {
    private final String accountNumber;
    protected double balance;
    private final List<Transaction> transactionHistory;
    private final AccountType type;

    protected Account(String accountNumber, double initialBalance, AccountType type) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.type = type;
        this.transactionHistory = new ArrayList<>();
        validateInitialBalance(initialBalance);
    }

    private void validateInitialBalance(double initialBalance) {
        if (initialBalance < type.getMinimumBalance()) {
            throw new IllegalArgumentException(String.format("Initial balance must be at least $%.2f for %s account", type.getMinimumBalance(), type)
            );
        }
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        recordTransaction(new Transaction(TransactionType.DEPOSIT, amount, balance));
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        if (balance - amount < type.getMinimumBalance()) {
            throw new InsufficientFundsException("Withdrawal would break minimum balance requirement");
        }
        balance -= amount;
        recordTransaction(new Transaction(TransactionType.WITHDRAWAL, amount, balance));
    }

    protected void recordTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
        TransactionLogger.getInstance().logTransaction(accountNumber, transaction);
    }

    public abstract void monthlyProcessing();

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public AccountType getType() {
        return type;
    }

    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }
}
