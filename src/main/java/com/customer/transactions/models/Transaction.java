package com.customer.transactions.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int amount;
    private String date;

    public Transaction() {
    }

    public Transaction(int amount, String date) {
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
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

    public LocalDate getLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return LocalDate.parse(this.date, formatter);
    }
}
