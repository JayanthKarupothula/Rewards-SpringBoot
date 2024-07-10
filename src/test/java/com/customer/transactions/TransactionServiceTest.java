package com.customer.transactions.services;

import com.customer.transactions.models.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionServiceTest {

    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        transactionService = new TransactionService();
    }

    @Test
    public void testGetTransactions() {
        List<Transaction> transactions = transactionService.getTransactions();
        assertEquals(7, transactions.size());
    }

    @Test
    public void testCalculateRewardPoints() {
        Transaction transaction1 = new Transaction(1, 120, "01-15-2023");
        Transaction transaction2 = new Transaction(1, 75, "02-13-2023");

        int points1 = transactionService.calculateRewardPoints(transaction1);
        int points2 = transactionService.calculateRewardPoints(transaction2);

        assertEquals(90, points1);
        assertEquals(25, points2);
    }

    @Test
    public void testCalculateMonthlyRewardPoints() {
        Map<Integer, Map<String, Integer>> monthlyPoints = transactionService.calculateMonthlyRewardPoints();

        assertEquals(2, monthlyPoints.size());

        assertEquals(280, monthlyPoints.get(1).get("JANUARY-2023"));
        assertEquals(25, monthlyPoints.get(1).get("FEBRUARY-2023"));
        assertEquals(250, monthlyPoints.get(1).get("MARCH-2023"));

        assertEquals(90, monthlyPoints.get(2).get("JANUARY-2023"));
        assertEquals(0, monthlyPoints.get(2).get("FEBRUARY-2023"));
        assertEquals(150, monthlyPoints.get(2).get("MARCH-2023"));
    }

    @Test
    public void testCalculateRewardPointsForAllCustomers() {
        Map<Integer, Integer> totalPoints = transactionService.calculateRewardPointsForAllCustomers();

        assertEquals(2, totalPoints.size());
        assertEquals(555, totalPoints.get(1).intValue());
        assertEquals(240, totalPoints.get(2).intValue());
    }
}
