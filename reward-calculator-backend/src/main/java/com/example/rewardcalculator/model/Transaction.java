package com.example.rewardcalculator.model;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private int customerId;
    private double amount;
    private LocalDateTime date;
    private int points;

    public int getPoints(){
        return points;
    }

    public void setPoints(int points){
        this.points = points;
    }
    // No-argument constructor
    public Transaction() {
    }

    // Constructor with arguments
    public Transaction(int id, int customerId, double amount, LocalDateTime date) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
