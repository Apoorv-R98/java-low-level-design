package com.photosharing.strategies;

import com.photosharing.models.Photo;

import java.util.List;

/**
 * Strategy interface for feed generation algorithms
 * Allows different strategies for ordering photos in user feeds
 */
public interface FeedStrategy {
    /**
     * Generate a feed for the user based on the strategy
     * @param userId The user requesting the feed
     * @param photos The collection of photos to order
     * @return Ordered list of photos based on the strategy
     */
    List<Photo> generateFeed(String userId, List<Photo> photos);
}

