package com.banking.model;

public class SavingsAccount extends Account {
    public SavingsAccount(String accountNumber, double initialBalance) {
        super(accountNumber, initialBalance, AccountType.SAVINGS);
    }

    @Override
    public void monthlyProcessing() {
        double interest = getType().calculateMonthlyInterest(getBalance());
        balance += interest;
        recordTransaction(new Transaction(TransactionType.INTEREST, interest, balance));
    }
}
