package com.banking.service;

import com.banking.exception.AccountNotFoundException;
import com.banking.exception.InvalidTransactionException;
import com.banking.model.Account;
import com.banking.model.AccountType;
import com.banking.model.CheckingAccount;
import com.banking.model.SavingsAccount;
import com.banking.util.AccountNumberGenerator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountService {
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final AccountNumberGenerator accountNumberGenerator;
    private final TransactionService transactionService;

    public AccountService() {
        this.accountNumberGenerator = new AccountNumberGenerator();
        this.transactionService = new TransactionService();
    }

//    public Account createAccount(AccountType type, double initialBalance) {
//        String accountNumber = accountNumberGenerator.generateAccountNumber(type);
//
//        Account account = switch (type) {
//            case SAVINGS -> new SavingsAccount(accountNumber, initialBalance);
//            case CHECKING -> new CheckingAccount(accountNumber, initialBalance);
//            case MONEY_MARKET -> throw new UnsupportedOperationException("Money Market accounts not implemented yet");
//        }; // Converted to Java 14
//
//        // Java 11 Version
//
//        accounts.put(accountNumber, account);
//        return account;
//    }

    // Version 2 of Create Account
    public Account createAccount(AccountType type, double initialBalance) throws InvalidTransactionException {
        String accountNumber = accountNumberGenerator.generateAccountNumber(type);

        if (accounts.containsKey(accountNumber)) {
            throw new InvalidTransactionException("Account number already exists");
        }

        Account account;

        switch (type) {
            case SAVINGS:
                account = new SavingsAccount(accountNumber, initialBalance);
                break;

            case CHECKING:
                account = new CheckingAccount(accountNumber, initialBalance);
                break;

            default:
                throw new InvalidTransactionException("Invalid account type");
        }

        accounts.put(accountNumber, account);
        return account;
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = getAccount(fromAccountNumber);
        Account toAccount = getAccount(toAccountNumber);

        transactionService.processTransfer(fromAccount, toAccount, amount);
    }

    private Account getAccount(String accountNumber) {
        return accounts.computeIfAbsent(accountNumber,
                k -> {
                    throw new AccountNotFoundException("Account not found: " + k);
                }
        );
    }

    public Account getAccountById(String accountNumber) {
        return getAccount(accountNumber);
    }
}
