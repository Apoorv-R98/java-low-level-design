package com.rating.services;

import com.rating.repositories.RatingRepository;
import com.rating.strategies.MonthlyBestAgentStrategy;
import com.rating.strategies.OverallAverageRankingStrategy;
import java.time.LocalDate;
import java.util.List;

public class RatingService {
    private final RatingRepository repo;

    public RatingService(RatingRepository repo) {
        this.repo = repo;
    }

    public void addRating(String agentId, int rating, LocalDate date) {
        com.rating.models.Rating r = RatingFactory.create(agentId, rating, date);
        repo.save(r);
    }

    public List<?> getOverallRanking() {
        return new OverallAverageRankingStrategy().rank(repo.findAll());
    }

    public List<?> getMonthlyBestAgents() {
        return new MonthlyBestAgentStrategy().rank(repo.findAll());
    }
}

