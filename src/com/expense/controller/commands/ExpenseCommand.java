package com.expense.controller.commands;

import com.expense.models.SplitType;
import com.expense.services.ExpenseService;

import java.util.List;

public class ExpenseCommand implements Command {

    private final ExpenseService expenseService;
    private final String paidById;
    private final double amount;
    private final java.util.List<String> userIds;
    private final SplitType splitType;
    private final java.util.List<Double> metadata;

    public ExpenseCommand(ExpenseService expenseService,
                          String paidById,
                          double amount,
                          List<String> userIds,
                          SplitType splitType,
                          List<Double> metadata) {
        this.expenseService = expenseService;
        this.paidById = paidById;
        this.amount = amount;
        this.userIds = userIds;
        this.splitType = splitType;
        this.metadata = metadata;
    }

    @Override
    public void execute() {
        expenseService.addExpense(paidById, amount, userIds, splitType, metadata);
    }
}
