package com.example.rewardcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import com.example.rewardcalculator.model.Transaction;
import com.example.rewardcalculator.service.RewardService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @PostMapping
    public int addTransaction(@RequestBody Transaction transaction) {
        rewardService.addTransaction(transaction);
        return rewardService.calculatePoints(transaction.getAmount());
    }

    @GetMapping("/monthly/{customerId}")
    public ResponseEntity<Map<String, Integer>> getMonthlyPoints(@PathVariable int customerId) {
        Map<String, Integer> monthlyPoints = rewardService.getMonthlyPoints(customerId);
        return new ResponseEntity<>(monthlyPoints, HttpStatus.OK);
    }

    @GetMapping("/total/{customerId}")
    public int getTotalPoints(@PathVariable int customerId) {
        return rewardService.getTotalPoints(customerId);
    }

    @GetMapping("/all/{customerId}")
    public List<Transaction> getAllTransactions(@PathVariable int customerId) {
        return rewardService.getAllTransactions(customerId);
    }

    @GetMapping("/all-monthly-points")
    public Map<Integer, Map<String, Integer>> getAllMonthlyPoints() {
        return rewardService.getAllMonthlyPoints();
    }

    @GetMapping("/all-total-points")
    public Map<Integer, Integer> getAllTotalPoints() {
        return rewardService.getAllTotalPoints();
    }

}
