package com.customer.transactions.service;

import com.customer.transactions.models.Transaction;
import com.customer.transactions.repository.TransactionRepository;
import com.customer.transactions.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public List<Transaction> getTransactions() {
        try {
            return transactionRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching transactions from the database", e);
            throw new RuntimeException("Database error: Unable to fetch transactions", e);
        }
    }

    public int calculateRewardPoints(Transaction transaction) {
        ValidationUtil.validateTransaction(transaction);

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

    public Map<String, Integer> calculateMonthlyRewardPoints() {
        String transactionId = UUID.randomUUID().toString();
        logger.info("Starting monthly reward points calculation. Transaction ID: {}", transactionId);

        Map<String, Integer> monthlyPoints = new HashMap<>();

        List<Transaction> transactions = getTransactions();

        for (Transaction transaction : transactions) {
            try {
                LocalDate date = transaction.getLocalDate();
                String month = date.getMonth().toString() + "-" + date.getYear();
                int points = calculateRewardPoints(transaction);

                monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + points);
            } catch (Exception e) {
                logger.error("Error processing transaction. Transaction ID: {}, Transaction: {}", transactionId, transaction, e);
            }
        }

        return monthlyPoints;
    }

    public int calculateRewardPointsForAllTransactions() {
        String transactionId = UUID.randomUUID().toString();
        logger.info("Starting total reward points calculation. Transaction ID: {}", transactionId);

        int totalPoints = 0;
        List<Transaction> transactions = getTransactions();

        for (Transaction transaction : transactions) {
            try {
                totalPoints += calculateRewardPoints(transaction);
            } catch (Exception e) {
                logger.error("Error processing transaction. Transaction ID: {}, Transaction: {}", transactionId, transaction, e);
            }
        }

        return totalPoints;
    }
}