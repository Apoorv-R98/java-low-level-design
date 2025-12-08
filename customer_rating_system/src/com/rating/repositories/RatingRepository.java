package com.rating.repositories;

import com.rating.models.Rating;
import java.util.List;

public interface RatingRepository {
    void save(Rating rating);
    List<Rating> findAll();
}

