package com.expense.services;

import com.expense.exceptions.InvalidSplitException;
import com.expense.models.*;

import java.util.ArrayList;
import java.util.List;

public class SplitFactory {

    public static List<Split> buildSplits(
            SplitType type,
            List<User> users,
            List<Double> metadata,
            double totalAmount
    ) {
        List<Split> splits = new ArrayList<>();

        switch (type) {
            case EQUAL -> {
                for (User user : users) {
                    Split split = new EqualSplit(user, users.size());
                    split.compute(totalAmount);
                    splits.add(split);
                }
            }
            case EXACT -> {
                if (metadata.size() != users.size()) {
                    throw new InvalidSplitException("Exact split values count must match number of users");
                }
                double sum = 0;
                for (Double d : metadata) sum += d;
                if (Math.abs(sum - totalAmount) > 0.001) {
                    throw new InvalidSplitException("Sum of exact split amounts (" + sum +
                            ") does not match total amount (" + totalAmount + ")");
                }
                for (int i = 0; i < users.size(); i++) {
                    Split split = new ExactSplit(users.get(i), metadata.get(i));
                    split.compute(totalAmount);
                    splits.add(split);
                }
            }
            case PERCENT -> {
                if (metadata.size() != users.size()) {
                    throw new InvalidSplitException("Percent split values count must match number of users");
                }
                double percentSum = 0;
                for (Double d : metadata) percentSum += d;
                if (Math.abs(percentSum - 100.0) > 0.001) {
                    throw new InvalidSplitException("Percent split values must sum to 100 (got " + percentSum + ")");
                }
                for (int i = 0; i < users.size(); i++) {
                    Split split = new PercentSplit(users.get(i), metadata.get(i));
                    split.compute(totalAmount);
                    splits.add(split);
                }
            }
            default -> throw new InvalidSplitException("Unsupported split type: " + type);
        }

        return splits;
    }
}
