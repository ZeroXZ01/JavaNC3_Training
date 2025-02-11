package com.training.week1;

import java.time.LocalDateTime;

public class Transaction {
    private TransactionType type;
    private double amount;
    private String description;
    private LocalDateTime timestamp;

    public Transaction(TransactionType type, double amount, String description, LocalDateTime timestamp) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = timestamp;
    }

    // Getters
    public TransactionType getType() { return type; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
