package com.photosharing.services;

import com.photosharing.exceptions.NotFoundException;
import com.photosharing.models.Photo;
import com.photosharing.models.User;
import com.photosharing.repositories.PhotoRepository;
import com.photosharing.repositories.UserRepository;
import com.photosharing.strategies.ChronologicalFeedStrategy;
import com.photosharing.strategies.FeedStrategy;

import java.util.ArrayList;
import java.util.List;

public class FeedService {
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;
    private FeedStrategy feedStrategy;

    public FeedService(PhotoRepository photoRepository, UserRepository userRepository) {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
        this.feedStrategy = new ChronologicalFeedStrategy();
    }

    public void setFeedStrategy(FeedStrategy feedStrategy) {
        this.feedStrategy = feedStrategy;
    }

    public List<Photo> getFeedForUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User", userId));

        List<Photo> feedPhotos = new ArrayList<>();
        
        for (String followingUserId : user.getFollowing()) {
            List<Photo> userPhotos = photoRepository.findByUserId(followingUserId);
            feedPhotos.addAll(userPhotos);
        }

        return feedStrategy.generateFeed(userId, feedPhotos);
    }
}
