package com.training.week1.day2;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// Bank class to manage all accounts
public class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    public void createAccount(String accountType, String accountNumber, double initialBalance) throws InvalidTransactionException {
        if (accounts.containsKey(accountNumber)) {
            throw new InvalidTransactionException("Account number already exists");
        }

        Account account;
        switch (accountType.toUpperCase()) {
            case "SAVINGS":
                account = new SavingsAccount(accountNumber, initialBalance);
                break;
            case "CHECKING":
                account = new CheckingAccount(accountNumber, initialBalance);
                break;
            default:
                throw new InvalidTransactionException("Invalid account type");
        }

        accounts.put(accountNumber, account);
    }
    public void monthlyProcessing() {
        for (Account account : accounts.values()) {
            account.monthlyProcessing();
        }
    }

    public Account getAccount(String accountNumber) throws InvalidTransactionException {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new InvalidTransactionException("Account not found");
        }
        return account;
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount)
            throws InvalidTransactionException {
        Account fromAccount = accounts.get(fromAccountNumber);
        Account toAccount = accounts.get(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            throw new InvalidTransactionException("Invalid account number");
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        fromAccount.transactionHistory.add(new Transaction(
                TransactionType.TRANSFER,
                amount,
                "Transfer to " + toAccountNumber,
                LocalDateTime.now()
        ));

        toAccount.transactionHistory.add(new Transaction(
                TransactionType.TRANSFER,
                amount,
                "Transfer from " + fromAccountNumber,
                LocalDateTime.now()
        ));

        // Add logging for transfer
        TransactionLogger.logTransaction(
                fromAccountNumber,
                TransactionType.TRANSFER,
                amount,
                fromAccount.getBalance(),
                "Transfer to " + toAccountNumber
        );

        TransactionLogger.logTransaction(
                toAccountNumber,
                TransactionType.TRANSFER,
                amount,
                toAccount.getBalance(),
                "Transfer from " + fromAccountNumber
        );
    }
}
