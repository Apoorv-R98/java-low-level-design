package com.rating.services;

import com.rating.exceptions.InvalidRatingException;
import com.rating.models.Rating;
import java.time.LocalDate;

public class RatingFactory {
    public static Rating create(String agentId, int value, LocalDate date) {
        if (agentId == null || agentId.isEmpty())
            throw new InvalidRatingException("Agent ID cannot be null or empty");
        if (value < 1 || value > 5)
            throw new InvalidRatingException("Rating must be between 1 and 5");
        if (date == null)
            throw new InvalidRatingException("Date cannot be null");
        return new Rating(agentId, value, date);
    }
}

