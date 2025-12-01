package com.expense.controller;

import com.expense.controller.commands.*;
import com.expense.models.SplitType;
import com.expense.services.ExpenseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CommandDispatcher {

    private final ExpenseService expenseService;

    public CommandDispatcher(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public Command parse(String line) {
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            return new InvalidCommand("Empty command");
        }

        String[] tokens = trimmed.split("\\s+");
        String keyword = tokens[0].toUpperCase(Locale.ROOT);

        return switch (keyword) {
            case "ADD_USER" -> parseAddUser(tokens);
            case "EXPENSE" -> parseExpense(tokens);
            case "SHOW" -> parseShow(tokens);
            case "SHOW_SUMMARY" -> new ShowSummaryCommand(expenseService);
            case "EXIT" -> new ExitCommand();
            case "HELP" -> new HelpCommand();
            default -> new InvalidCommand("Unknown command: " + tokens[0]);
        };
    }

    private Command parseAddUser(String[] tokens) {
        if (tokens.length < 5) {
            return new InvalidCommand("Invalid ADD_USER syntax");
        }
        String id = tokens[1];
        String name = tokens[2];
        String email = tokens[3];
        String phone = tokens[4];
        return new AddUserCommand(expenseService, id, name, email, phone);
    }

    private Command parseExpense(String[] tokens) {
        if (tokens.length < 6) {
            return new InvalidCommand("Invalid EXPENSE syntax");
        }

        String paidBy = tokens[1];
        double amount;
        int numUsers;
        try {
            amount = Double.parseDouble(tokens[2]);
            numUsers = Integer.parseInt(tokens[3]);
        } catch (NumberFormatException e) {
            return new InvalidCommand("Invalid amount or number_of_users in EXPENSE");
        }

        if (tokens.length < 4 + numUsers + 1) {
            return new InvalidCommand("Not enough arguments for user IDs and split type");
        }

        List<String> userIds = new ArrayList<>();
        int idx = 4;
        for (int i = 0; i < numUsers; i++) {
            userIds.add(tokens[idx++]);
        }

        String splitTypeStr = tokens[idx++];
        SplitType splitType;
        try {
            splitType = SplitType.fromString(splitTypeStr);
        } catch (IllegalArgumentException e) {
            return new InvalidCommand("Invalid split type: " + splitTypeStr);
        }

        List<Double> metadata = new ArrayList<>();
        while (idx < tokens.length) {
            try {
                metadata.add(Double.parseDouble(tokens[idx]));
            } catch (NumberFormatException e) {
                return new InvalidCommand("Invalid numeric value in split details: " + tokens[idx]);
            }
            idx++;
        }

        return new ExpenseCommand(expenseService, paidBy, amount, userIds, splitType, metadata);
    }

    private Command parseShow(String[] tokens) {
        if (tokens.length == 1) {
            return new ShowCommand(expenseService, null);
        } else {
            return new ShowCommand(expenseService, tokens[1]);
        }
    }
}
