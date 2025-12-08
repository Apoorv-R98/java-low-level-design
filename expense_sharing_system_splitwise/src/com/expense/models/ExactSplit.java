package com.expense.models;

public class ExactSplit extends Split {

    private final double exactAmount;

    public ExactSplit(User user, double exactAmount) {
        super(user);
        this.exactAmount = exactAmount;
    }

    @Override
    public void compute(double totalAmount) {
        this.amount = exactAmount;
    }
}
