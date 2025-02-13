package com.banking.model;

import java.time.LocalDateTime;

public class Transaction {
    private final TransactionType type;
    private final double amount;
    private final double resultingBalance;
    private final LocalDateTime timestamp;

    public Transaction(TransactionType type, double amount, double resultingBalance) {
        this.type = type;
        this.amount = amount;
        this.resultingBalance = resultingBalance;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public TransactionType getType() { return type; }
    public double getAmount() { return amount; }
    public double getResultingBalance() { return resultingBalance; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
