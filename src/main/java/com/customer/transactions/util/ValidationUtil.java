package com.customer.transactions.util;

import com.customer.transactions.models.Transaction;

public class ValidationUtil {
    public static void validateTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }
        if (transaction.getCustomerId() <= 0) {
            throw new IllegalArgumentException("Invalid customer ID");
        }
        if (transaction.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (transaction.getDate() == null || transaction.getDate().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
    }
}
