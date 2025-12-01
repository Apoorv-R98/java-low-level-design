package com.expense.models;

public class EqualSplit extends Split {

    private final int totalUsers;

    public EqualSplit(User user, int totalUsers) {
        super(user);
        this.totalUsers = totalUsers;
    }

    @Override
    public void compute(double totalAmount) {
        this.amount = totalUsers == 0 ? 0 : totalAmount / totalUsers;
    }
}
