package com.expense.controller.commands;

import com.expense.services.ExpenseService;

public class ShowCommand implements Command {

    private final ExpenseService expenseService;
    private final String userId; // can be null for all

    public ShowCommand(ExpenseService expenseService, String userId) {
        this.expenseService = expenseService;
        this.userId = userId;
    }

    @Override
    public void execute() {
        expenseService.showBalances(userId);
    }
}
