package com.training.week1;

import java.time.LocalDateTime;

// Savings Account implementation
public class SavingsAccount extends Account{
    private static final double MINIMUM_BALANCE = 100.0;
    private static final double INTEREST_RATE = 0.025; // 2.5%

    public SavingsAccount(String accountNumber, double initialBalance) throws InvalidTransactionException {
        super(accountNumber, initialBalance);
        if (initialBalance < MINIMUM_BALANCE) {
            throw new InvalidTransactionException("Initial balance must be at least $" + MINIMUM_BALANCE);
        }
    }

    @Override
    public void withdraw(double amount) throws InvalidTransactionException {
        if (balance - amount < MINIMUM_BALANCE) {
            throw new InvalidTransactionException("Withdrawal would break minimum balance requirement");
        }
        super.withdraw(amount);
    }

    @Override
    public void monthlyProcessing() {
        double interest = balance * INTEREST_RATE / 12; // Monthly interest
        balance += interest;
        transactionHistory.add(new Transaction(TransactionType.INTEREST, interest, "Monthly interest", LocalDateTime.now()));
    }
}
