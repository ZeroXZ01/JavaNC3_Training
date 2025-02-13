package com.training.week1.day2;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionLogger {
    private static final String LOG_FILE = "bank_transactions.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void logTransaction(String accountNumber, TransactionType type, double amount, double balanceAfter, String description) {
        LocalDateTime timestamp = LocalDateTime.now();
        String logEntry = String.format("[%s] Account: %s | Type: %s | Amount: $%.2f | Balance: $%.2f | %s%n",
                timestamp.format(formatter),
                accountNumber,
                type,
                amount,
                balanceAfter,
                description
        );

        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(logEntry);
        } catch (IOException e) {
            System.err.println("Error writing to transaction log: " + e.getMessage());
        }
    }

    public static List<String> getTransactionHistory() {
        List<String> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                transactions.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading transaction log: " + e.getMessage());
        }
        return transactions;
    }

    public static List<String> getAccountTransactions(String accountNumber) {
        List<String> accountTransactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Account: " + accountNumber)) {
                    accountTransactions.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading transaction log: " + e.getMessage());
        }
        return accountTransactions;
    }
}
