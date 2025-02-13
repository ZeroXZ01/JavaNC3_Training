package com.training.week1.day2;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Transaction Logger class
public class TransactionLogger {
    private static final String LOG_FILE = "transaction_log.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void logTransaction(String paymentMethod, String transactionType, double amount, String status) {
        LocalDateTime timestamp = LocalDateTime.now();
        String logEntry = String.format("[%s] %s - %s: $%.2f - %s%n", timestamp.format(formatter), paymentMethod, transactionType, amount, status
        );

        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(logEntry);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    public static List<String> readTransactionLog() {
        List<String> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                transactions.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading log file: " + e.getMessage());
        }
        return transactions;
    }
}
