package com.photosharing.strategies;

import com.photosharing.models.Photo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChronologicalFeedStrategy implements FeedStrategy {
    
    @Override
    public List<Photo> generateFeed(String userId, List<Photo> photos) {
        return photos.stream()
                    .sorted(Comparator.comparing(Photo::getTimestamp).reversed())
                    .collect(Collectors.toList());
    }
}

