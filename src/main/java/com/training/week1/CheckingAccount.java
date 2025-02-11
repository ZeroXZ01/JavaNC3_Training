package com.training.week1;

import java.time.LocalDateTime;

// Checking Account implementation
public class CheckingAccount extends Account{
    private static final double MONTHLY_FEE = 12.0;
    private static final int MONTHLY_TRANSACTION_LIMIT = 100;
    private int transactionsThisMonth;

    public CheckingAccount(String accountNumber, double initialBalance) {
        super(accountNumber, initialBalance);
        this.transactionsThisMonth = 0;
    }

    @Override
    public void deposit(double amount) throws InvalidTransactionException {
        checkTransactionLimit();
        super.deposit(amount);
        transactionsThisMonth++;
    }

    @Override
    public void withdraw(double amount) throws InvalidTransactionException {
        checkTransactionLimit();
        super.withdraw(amount);
        transactionsThisMonth++;
    }

    private void checkTransactionLimit() throws InvalidTransactionException {
        if (transactionsThisMonth >= MONTHLY_TRANSACTION_LIMIT) {
            throw new InvalidTransactionException("Monthly transaction limit reached");
        }
    }

    @Override
    public void monthlyProcessing() {
        balance -= MONTHLY_FEE;
        transactionsThisMonth = 0;
        transactionHistory.add(new Transaction(TransactionType.FEE, MONTHLY_FEE, "Monthly maintenance fee", LocalDateTime.now()));
    }
}
