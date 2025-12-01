package com.expense.controller.commands;

import com.expense.services.ExpenseService;

public class AddUserCommand implements Command {

    private final ExpenseService expenseService;
    private final String id;
    private final String name;
    private final String email;
    private final String phone;

    public AddUserCommand(ExpenseService expenseService, String id, String name, String email, String phone) {
        this.expenseService = expenseService;
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public void execute() {
        expenseService.addUser(id, name, email, phone);
    }
}
