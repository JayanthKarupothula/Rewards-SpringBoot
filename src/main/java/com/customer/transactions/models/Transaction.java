package com.customer.transactions.models;
public class Transaction {
    private int customerId;
    private int amount;
    private String date;

    public Transaction(int customerId, int amount, String date) {
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int calculateRewardPoints() {
        int points = 0;
        if (amount > 100) {
            points += (amount - 100) * 2;
            points += 50; // 1 point for each dollar spent between $50 and $100
        } else if (amount > 50) {
            points += (amount - 50);
        }
        return points;
    }
}
