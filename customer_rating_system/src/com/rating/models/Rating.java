package com.rating.models;

import java.time.LocalDate;

public class Rating {
    private final String agentId;
    private final int rating;
    private final LocalDate date;

    public Rating(String agentId, int rating, LocalDate date) {
        this.agentId = agentId;
        this.rating = rating;
        this.date = date;
    }

    public String getAgentId() { return agentId; }
    public int getRating() { return rating; }
    public LocalDate getDate() { return date; }
}

