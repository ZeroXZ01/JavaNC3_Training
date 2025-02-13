package com.banking.model;

public enum AccountType {
    SAVINGS("SAV", 100.0, 0.025),
    CHECKING("CHK", 0.0, 0.0);

    private final String code;
    private final double minimumBalance;
    private final double annualInterestRate;

    AccountType(String code, double minimumBalance, double annualInterestRate) {
        this.code = code;
        this.minimumBalance = minimumBalance;
        this.annualInterestRate = annualInterestRate;
    }

    public String getCode() {
        return code;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public double calculateMonthlyInterest(double balance) {
        return balance * annualInterestRate / 12;
    }
}
