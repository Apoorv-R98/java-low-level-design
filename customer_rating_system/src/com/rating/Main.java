package com.rating;

import com.rating.repositories.InMemoryRatingRepository;
import com.rating.repositories.RatingRepository;
import com.rating.services.RatingService;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        RatingRepository repo = new InMemoryRatingRepository();
        RatingService service = new RatingService(repo);

        // Add sample ratings
        service.addRating("A1", 5, LocalDate.of(2025, 1, 15));
        service.addRating("A2", 4, LocalDate.of(2025, 1, 20));
        service.addRating("A1", 3, LocalDate.of(2025, 2, 10));
        service.addRating("A2", 5, LocalDate.of(2025, 2, 12));
        service.addRating("A3", 4, LocalDate.of(2025, 1, 25));
        service.addRating("A3", 5, LocalDate.of(2025, 2, 5));

        System.out.println("=== Overall Ranking ===");
        service.getOverallRanking().forEach(System.out::println);

        System.out.println("\n=== Monthly Best Agents ===");
        service.getMonthlyBestAgents().forEach(System.out::println);
    }
}

