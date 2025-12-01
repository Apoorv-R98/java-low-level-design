package com.expense.models;

import java.util.List;

public class Expense {
    private final String paidByUserId;
    private final double amount;
    private final java.util.List<Split> splits;

    public Expense(String paidByUserId, double amount, List<Split> splits) {
        this.paidByUserId = paidByUserId;
        this.amount = amount;
        this.splits = splits;
    }

    public String getPaidByUserId() {
        return paidByUserId;
    }

    public double getAmount() {
        return amount;
    }

    public java.util.List<Split> getSplits() {
        return splits;
    }
}
