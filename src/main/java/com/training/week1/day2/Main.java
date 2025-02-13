package com.training.week1.day2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Bank bank = new Bank();

            // Create accounts
            bank.createAccount("SAVINGS", "SAV001", 1000.0);
            bank.createAccount("CHECKING", "CHK001", 500.0);

            // Perform operations
            bank.getAccount("SAV001").deposit(200.0);
            bank.transfer("SAV001", "CHK001", 300.0);

            // Process monthly activities
            bank.monthlyProcessing();

            // Print transaction history from log file
            System.out.println("\nTransaction History from Log File:");
            List<String> transactions = TransactionLogger.getTransactionHistory();
            for (String transaction : transactions) {
                System.out.println(transaction);
            }

            // Print transactions for specific account
            System.out.println("\nSAV001 Transactions:");
            List<String> accountTransactions = TransactionLogger.getAccountTransactions("SAV001");
            for (String transaction : accountTransactions) {
                System.out.println(transaction);
            }

        } catch (InvalidTransactionException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
