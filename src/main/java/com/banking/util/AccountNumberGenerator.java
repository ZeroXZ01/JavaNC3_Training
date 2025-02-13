package com.banking.util;

import com.banking.model.AccountType;

import java.util.concurrent.atomic.AtomicInteger;

public class AccountNumberGenerator {
    private final AtomicInteger counter = new AtomicInteger(1);

    public String generateAccountNumber(AccountType type) {
        return String.format("%s%05d", type.getCode(), counter.getAndIncrement());
    }
}
