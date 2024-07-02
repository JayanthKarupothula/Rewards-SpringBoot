package com.customer.transactions.services;
import org.springframework.stereotype.Service;
import com.customer.transactions.models.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class TransactionService {
    private List<Transaction> transactions = Arrays.asList(
            new Transaction(1, 120, "01-15-2023"),
            new Transaction(1, 170, "01-16-2023"),
            new Transaction(1, 75, "02-13-2023"),
            new Transaction(1, 200, "03-11-2023"),
            new Transaction(2, 120, "01-15-2023"),
            new Transaction(2, 50, "02-13-2023"),
            new Transaction(2, 150, "03-11-2023")
    );

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public int calculateRewardPoints(Transaction transaction) {
        int points = 0;
        int amount = transaction.getAmount();
        if (amount > 100) {
            points += (amount - 100) * 2;
            points += 50; // 1 point for each dollar spent between $50 and $100
        } else if (amount > 50) {
            points += (amount - 50);
        }
        return points;
    }

    public Map<Integer, Map<String, Integer>> calculateMonthlyRewardPoints() {
        Map<Integer, Map<String, Integer>> customerPoints = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

        for (Transaction transaction : transactions) {
            int customerId = transaction.getCustomerId();
            LocalDate date = LocalDate.parse(transaction.getDate(), formatter);
            String month = date.getMonth().toString() + "-" + date.getYear();
            int points = calculateRewardPoints(transaction);

            customerPoints.putIfAbsent(customerId, new HashMap<>());
            Map<String, Integer> monthlyPoints = customerPoints.get(customerId);

            monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + points);
        }

        return customerPoints;
    }

    public Map<Integer, Integer> calculateRewardPointsForAllCustomers() {
        Map<Integer, Integer> customerPoints = new HashMap<>();
        for (Transaction transaction : transactions) {
            int customerId = transaction.getCustomerId();
            int points = calculateRewardPoints(transaction);
            customerPoints.put(customerId, customerPoints.getOrDefault(customerId, 0) + points);
        }
        return customerPoints;
    }
}