package com.expense.services;

import com.expense.exceptions.DuplicateUserException;
import com.expense.exceptions.UserNotFoundException;
import com.expense.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseService {

    private final Map<String, User> users = new HashMap<>();
    private final LedgerService ledgerService;

    public ExpenseService(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    public void addUser(String id, String name, String email, String phone) {
        if (users.containsKey(id)) {
            throw new DuplicateUserException("User already exists: " + id);
        }
        users.put(id, new User(id, name, email, phone));
    }

    public void addExpense(String paidById,
                           double amount,
                           List<String> userIds,
                           SplitType splitType,
                           List<Double> metadata) {

        if (!users.containsKey(paidById)) {
            throw new UserNotFoundException("Payer not found: " + paidById);
        }

        List<User> participants = new ArrayList<>();
        for (String uid : userIds) {
            User user = users.get(uid);
            if (user == null) {
                throw new UserNotFoundException("User not found: " + uid);
            }
            participants.add(user);
        }

        List<Split> splits = SplitFactory.buildSplits(splitType, participants, metadata, amount);
        Expense expense = new Expense(paidById, amount, splits);

        for (Split split : expense.getSplits()) {
            String borrower = split.getUser().getId();
            if (!borrower.equals(paidById)) {
                ledgerService.addDebt(borrower, paidById, split.getAmount());
            }
        }
    }

    public void showBalances(String userId) {
        if (userId == null) {
            ledgerService.showAllBalances();
        } else {
            ledgerService.showUserBalances(userId);
        }
    }

    public void showSummary() {
        System.out.println("---- BALANCE SUMMARY ----");
        ledgerService.showAllBalances();
    }
}
