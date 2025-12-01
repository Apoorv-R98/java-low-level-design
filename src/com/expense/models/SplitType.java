package com.expense.models;

public enum SplitType {
    EQUAL,
    EXACT,
    PERCENT;

    public static SplitType fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Split type cannot be null");
        }
        return SplitType.valueOf(value.toUpperCase());
    }
}
