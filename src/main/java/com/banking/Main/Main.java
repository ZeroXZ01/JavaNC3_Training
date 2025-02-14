package com.banking.Main;

import com.banking.exception.InvalidTransactionException;
import com.banking.model.Account;
import com.banking.model.AccountType;
import com.banking.service.AccountService;
import com.banking.util.TransactionLogger;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();

        try {
            // Create accounts
            Account savings = accountService.createAccount(AccountType.SAVINGS, 1000);
            Account checking = accountService.createAccount(AccountType.CHECKING, 500);

            // Perform operations
            accountService.transfer(savings.getAccountNumber(), checking.getAccountNumber(), 300);

            // Print balances
            System.out.printf("Savings %s balance: $%.2f%n", savings.getAccountNumber(), savings.getBalance());
            System.out.printf("Checking %s balance: $%.2f%n", checking.getAccountNumber(), checking.getBalance());

            // Process monthly activities
            savings.monthlyProcessing();
            checking.monthlyProcessing();

            // Print transaction history from log file
            System.out.println("\nTransaction History from Log File:");
            List<String> transactions = TransactionLogger.getTransactionHistory();
            for (String transaction : transactions) {
                System.out.println(transaction);
            }

            // Print transactions for specific account
            System.out.println("\nSAV00001 Transactions:");
            List<String> accountTransactions = TransactionLogger.getAccountTransactions("SAV00001");
            for (String transaction : accountTransactions) {
                System.out.println(transaction);
            }

        } catch (InvalidTransactionException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
