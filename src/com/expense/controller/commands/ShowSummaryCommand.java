package com.expense.controller.commands;

import com.expense.services.ExpenseService;

public class ShowSummaryCommand implements Command {

    private final ExpenseService expenseService;

    public ShowSummaryCommand(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Override
    public void execute() {
        expenseService.showSummary();
    }
}
