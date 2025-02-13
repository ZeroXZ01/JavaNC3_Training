package com.banking.model;

import com.banking.exception.TransactionLimitException;

public class CheckingAccount extends Account {
    private static final double MONTHLY_FEE = 12.0;
    private static final int MONTHLY_TRANSACTION_LIMIT = 100;
    private int transactionCount;

    public CheckingAccount(String accountNumber, double initialBalance) {
        super(accountNumber, initialBalance, AccountType.CHECKING);
        this.transactionCount = 0;
    }

    @Override
    public void deposit(double amount) {
        checkTransactionLimit();
        super.deposit(amount);
        transactionCount++;
    }

    @Override
    public void withdraw(double amount) {
        checkTransactionLimit();
        super.withdraw(amount);
        transactionCount++;
    }

    private void checkTransactionLimit() {
        if (transactionCount >= MONTHLY_TRANSACTION_LIMIT) {
            throw new TransactionLimitException("Monthly transaction limit reached");
        }
    }

    @Override
    public void monthlyProcessing() {
        balance -= MONTHLY_FEE;
        recordTransaction(new Transaction(TransactionType.FEE, MONTHLY_FEE, balance));
        transactionCount = 0;
    }
}
