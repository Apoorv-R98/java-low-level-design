package com.expense.models;

public abstract class Split {
    protected final User user;
    protected double amount;

    public Split(User user) {
        this.user = user;
    }

    public abstract void compute(double totalAmount);

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }
}
