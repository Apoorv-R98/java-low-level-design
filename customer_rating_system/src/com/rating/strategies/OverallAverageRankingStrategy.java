package com.rating.strategies;

import com.rating.models.Rating;
import com.rating.models.dto.AgentAverageDTO;
import java.util.*;
import java.util.stream.Collectors;

public class OverallAverageRankingStrategy implements RankingStrategy {
    @Override
    public List<AgentAverageDTO> rank(List<Rating> ratings) {
        Map<String, List<Rating>> grouped = ratings.stream()
                .collect(Collectors.groupingBy(Rating::getAgentId));
        return grouped.entrySet().stream()
                .map(e -> {
                    double avg = e.getValue().stream()
                            .mapToInt(Rating::getRating)
                            .average().orElse(0.0);

                    return AgentAverageDTO.builder()
                            .agentId(e.getKey())
                            .averageRating(avg)
                            .build();
                })
                .sorted((a, b) -> Double.compare(b.getAverageRating(), a.getAverageRating()))
                .collect(Collectors.toList());
    }
}

