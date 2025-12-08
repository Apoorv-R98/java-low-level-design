package com.rating.strategies;

import com.rating.models.Rating;
import com.rating.models.dto.MonthlyBestAgentDTO;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class MonthlyBestAgentStrategy implements RankingStrategy {
    @Override
    public List<MonthlyBestAgentDTO> rank(List<Rating> ratings) {

        Map<YearMonth, Map<String, List<Rating>>> grouped =
            ratings.stream().collect(
                Collectors.groupingBy(
                    r -> YearMonth.from(r.getDate()),
                    Collectors.groupingBy(Rating::getAgentId)
                )
            );

        List<MonthlyBestAgentDTO> output = new ArrayList<>();

        for (YearMonth month : grouped.keySet().stream().sorted().toList()) {
            Map<String, List<Rating>> byAgent = grouped.get(month);
            String bestAgent = null;
            double bestAvg = 0.0;

            for (var entry : byAgent.entrySet()) {
                double avg = entry.getValue().stream()
                                  .mapToInt(Rating::getRating)
                                  .average().orElse(0.0);

                if (bestAgent == null || avg > bestAvg) {
                    bestAgent = entry.getKey();
                    bestAvg = avg;
                }
            }

            output.add(
                MonthlyBestAgentDTO.builder()
                    .month(month)
                    .agentId(bestAgent)
                    .averageRating(bestAvg)
                    .build()
            );
        }
        return output;
    }
}

