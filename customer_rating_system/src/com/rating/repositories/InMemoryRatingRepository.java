package com.rating.repositories;

import com.rating.models.Rating;
import java.util.ArrayList;
import java.util.List;

public class InMemoryRatingRepository implements RatingRepository {
    private final List<Rating> ratings = new ArrayList<>();

    @Override
    public void save(Rating rating) {
        ratings.add(rating);
    }

    @Override
    public List<Rating> findAll() {
        return new ArrayList<>(ratings);
    }
}

