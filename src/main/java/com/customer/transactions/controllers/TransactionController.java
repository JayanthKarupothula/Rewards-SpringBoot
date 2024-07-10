package com.customer.transactions.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import com.customer.transactions.models.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.customer.transactions.services.TransactionService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }

    @GetMapping("/transactions/rewards/monthly")
    public Map<Integer, Map<String, Integer>> getMonthlyRewardPoints() {
        return transactionService.calculateMonthlyRewardPoints();
    }


    @GetMapping("/transactions/rewards/total")
    public Map<Integer, Integer> getRewardPointsForAllCustomers() {
        return transactionService.calculateRewardPointsForAllCustomers();
    }
}