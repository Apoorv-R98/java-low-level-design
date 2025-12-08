package com.rating.strategies;

import com.rating.models.Rating;
import java.util.List;

public interface RankingStrategy {
    List<?> rank(List<Rating> ratings);
}

