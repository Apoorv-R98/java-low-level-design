package com.expense.models;

public class PercentSplit extends Split {

    private final double percent;

    public PercentSplit(User user, double percent) {
        super(user);
        this.percent = percent;
    }

    @Override
    public void compute(double totalAmount) {
        this.amount = (totalAmount * percent) / 100.0;
    }
}
