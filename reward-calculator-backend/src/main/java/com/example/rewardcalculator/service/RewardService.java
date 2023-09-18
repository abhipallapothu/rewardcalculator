package com.example.rewardcalculator.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.rewardcalculator.model.Transaction;

@Service
public class RewardService {
    private final List<Transaction> transactions = new ArrayList<>();

    public int calculatePoints(double amount) {
        int points = 0;

        if (amount > 100) {
            points += (int) ((amount - 100) * 2);  // 2 points for every dollar over $100
            points += (int) ((100 - 50));  // 1 point for every dollar between $51 and $100
        } else if (amount > 50) {
            points += (int) ((amount - 50));  // 1 point for every dollar over $50 up to $100
        }

        return points;
    }


    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public Map<String, Integer> getMonthlyPoints(int customerId) {
        Map<String, Integer> monthlyPoints = new HashMap<>();
        for (Transaction t : transactions) {
            if (t.getCustomerId() == customerId) {
                String month = t.getDate().getYear() + "-" + t.getDate().getMonthValue();
                monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + calculatePoints(t.getAmount()));
            }
        }
        return monthlyPoints;
    }

    public int getTotalPoints(int customerId) {
        return transactions.stream()
                .filter(t -> t.getCustomerId() == customerId)
                .mapToInt(t -> calculatePoints(t.getAmount()))
                .sum();
    }

    public List<Transaction> getAllTransactions(int customerId) {
        return transactions.stream()
                .filter(t -> t.getCustomerId() == customerId)
                .peek(t -> t.setPoints(calculatePoints(t.getAmount())))
                .collect(Collectors.toList());
    }

    public Map<Integer, Map<String, Integer>> getAllMonthlyPoints() {
        Map<Integer, Map<String, Integer>> allMonthlyPoints = new HashMap<>();
        for (Transaction t : transactions) {
            int customerId = t.getCustomerId();
            String month = t.getDate().getYear() + "-" + t.getDate().getMonthValue();

            allMonthlyPoints.putIfAbsent(customerId, new HashMap<>());
            Map<String, Integer> monthlyPoints = allMonthlyPoints.get(customerId);

            monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + calculatePoints(t.getAmount()));
        }
        return allMonthlyPoints;
    }


    public Map<Integer, Integer> getAllTotalPoints() {
        Map<Integer, Integer> allTotalPoints = new HashMap<>();
        for (Transaction t : transactions) {
            int customerId = t.getCustomerId();
            allTotalPoints.put(customerId, allTotalPoints.getOrDefault(customerId, 0) + calculatePoints(t.getAmount()));
        }
        return allTotalPoints;
    }



    @PostConstruct
    public void init() {
        transactions.add(new Transaction(1, 1, 120.0, LocalDateTime.of(2023, 7, 1, 0, 0)));
        transactions.add(new Transaction(2, 1, 180.0, LocalDateTime.of(2023, 7, 2, 0, 0)));
        transactions.add(new Transaction(3, 2, 200.0, LocalDateTime.of(2023, 7, 1, 0, 0)));
        transactions.add(new Transaction(4, 2, 400.0, LocalDateTime.of(2023, 8, 1, 0, 0)));
        transactions.add(new Transaction(5, 3, 550.0, LocalDateTime.of(2023, 7, 1, 0, 0)));
        transactions.add(new Transaction(6, 3, 490.0, LocalDateTime.of(2023, 8, 1, 0, 0)));
        transactions.add(new Transaction(7, 3, 510.0, LocalDateTime.of(2023, 8, 1, 0, 0)));
        transactions.add(new Transaction(8, 1, 75.0, LocalDateTime.of(2023, 7, 15, 0, 0)));
        transactions.add(new Transaction(9, 1, 230.0, LocalDateTime.of(2023, 8, 20, 0, 0)));
        transactions.add(new Transaction(10, 2, 340.0, LocalDateTime.of(2023, 7, 10, 0, 0)));
        transactions.add(new Transaction(11, 2, 380.0, LocalDateTime.of(2023, 7, 25, 0, 0)));
        transactions.add(new Transaction(12, 4, 800.0, LocalDateTime.of(2023, 8, 1, 0, 0)));
        transactions.add(new Transaction(13, 4, 1000.0, LocalDateTime.of(2023, 9, 1, 0, 0)));
        transactions.add(new Transaction(14, 5, 50.0, LocalDateTime.of(2023, 7, 1, 0, 0)));
        transactions.add(new Transaction(15, 5, 100.0, LocalDateTime.of(2023, 7, 15, 0, 0)));
        transactions.add(new Transaction(16, 5, 250.0, LocalDateTime.of(2023, 7, 30, 0, 0)));
        transactions.add(new Transaction(17, 1, 690.0, LocalDateTime.of(2023, 6, 30, 0, 0)));
        // Additional sample transactions
    }

}
