package com.expense.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LedgerService {

    // balances[borrower][lender] = amount borrower owes lender
    private final Map<String, Map<String, Double>> balances = new HashMap<>();

    public void addDebt(String borrowerId, String lenderId, double amount) {
        if (borrowerId.equals(lenderId) || amount <= 0) {
            return;
        }

        balances.putIfAbsent(borrowerId, new HashMap<>());
        balances.putIfAbsent(lenderId, new HashMap<>());

        double forward = balances.get(borrowerId).getOrDefault(lenderId, 0.0) + amount;
        double reverse = balances.get(lenderId).getOrDefault(borrowerId, 0.0);

        // Netting off mutual debts
        if (reverse > 0) {
            if (reverse >= forward) {
                balances.get(lenderId).put(borrowerId, round2(reverse - forward));
                forward = 0;
            } else {
                forward -= reverse;
                balances.get(lenderId).put(borrowerId, 0.0);
            }
        }

        if (forward > 0) {
            balances.get(borrowerId).put(lenderId, round2(forward));
        } else {
            balances.get(borrowerId).put(lenderId, 0.0);
        }
    }

    public void showAllBalances() {
        boolean any = false;
        for (String user : balances.keySet()) {
            any |= printUserBalances(user);
        }
        if (!any) {
            System.out.println("No Balances");
        }
    }

    public void showUserBalances(String userId) {
        boolean any = printUserBalances(userId);
        if (!any) {
            System.out.println("No Balances");
        }
    }

    private boolean printUserBalances(String userId) {
        boolean printed = false;
        Map<String, Double> map = balances.getOrDefault(userId, Collections.emptyMap());
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            double amount = entry.getValue();
            if (amount > 0.0) {
                System.out.println(userId + " owes " + entry.getKey() + ": " + formatAmount(amount));
                printed = true;
            }
        }
        return printed;
    }

    private static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    private static String formatAmount(double v) {
        if (Math.abs(v - Math.rint(v)) < 0.001) {
            return String.valueOf((int) Math.rint(v));
        }
        return String.format(Locale.ROOT, "%.2f", v);
    }
}
