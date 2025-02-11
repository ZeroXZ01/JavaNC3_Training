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

            // Print balances
            System.out.println("SAV001 Balance: $" + bank.getAccount("SAV001").getBalance());
            System.out.println("CHK001 Balance: $" + bank.getAccount("CHK001").getBalance());

            // Process monthly activities
            bank.monthlyProcessing();

            // Print updated balances
            System.out.println("\nAfter monthly processing:");
            System.out.println("SAV001 Balance: $" + bank.getAccount("SAV001").getBalance());
            System.out.println("CHK001 Balance: $" + bank.getAccount("CHK001").getBalance());

            // Print transaction history for SAV001
            System.out.println("\nTransaction History for SAV001:");
            List<Transaction> transactions = bank.getAccount("SAV001").getTransactionHistory();
            for (Transaction transaction : transactions) {
                System.out.println(transaction.getType() + ": $" + transaction.getAmount() + " - " + transaction.getDescription());
            }

        } catch (InvalidTransactionException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
