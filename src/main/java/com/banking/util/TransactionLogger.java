package com.banking.util;

import com.banking.model.Transaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;

public class TransactionLogger {
    private static final TransactionLogger INSTANCE = new TransactionLogger();
    private static final String LOG_FILE = "transactions.log";
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private TransactionLogger() {}

    public static TransactionLogger getInstance() {
        return INSTANCE;
    }

    public synchronized void logTransaction(String accountNumber, Transaction transaction) {
        String logEntry = formatLogEntry(accountNumber, transaction);
        try {
            Files.write(
                    Paths.get(LOG_FILE),
                    logEntry.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.err.println("Failed to log transaction: " + e.getMessage());
        }
    }

    private String formatLogEntry(String accountNumber, Transaction transaction) {
        return String.format("[%s] Account: %s | Type: %s | Amount: $%.2f | Balance: $%.2f%n",
                transaction.getTimestamp().format(DATE_FORMATTER),
                accountNumber,
                transaction.getType(),
                transaction.getAmount(),
                transaction.getResultingBalance());
    }
}
