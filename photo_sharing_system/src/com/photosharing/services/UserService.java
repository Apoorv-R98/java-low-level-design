package com.photosharing.services;

import com.photosharing.exceptions.*;
import com.photosharing.models.User;
import com.photosharing.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(String userId, String username) {
        if (userRepository.existsById(userId)) {
            throw new InvalidOperationException("User already exists with ID: " + userId);
        }
        
        User user = new User(userId, username);
        return userRepository.save(user);
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User", userId));
    }

    public void followUser(String followerId, String followeeId) {
        if (followerId.equals(followeeId)) {
            throw new InvalidOperationException("User cannot follow themselves");
        }

        User follower = getUserById(followerId);
        User followee = getUserById(followeeId);

        if (follower.isFollowing(followeeId)) {
            throw new InvalidOperationException("User " + followerId + " is already following user " + followeeId);
        }

        follower.addFollowing(followeeId);
        followee.addFollower(followerId);

        userRepository.save(follower);
        userRepository.save(followee);
    }

    public void unfollowUser(String followerId, String followeeId) {
        User follower = getUserById(followerId);
        User followee = getUserById(followeeId);

        if (!follower.isFollowing(followeeId)) {
            throw new InvalidOperationException("User " + followerId + " is not following user " + followeeId);
        }

        follower.removeFollowing(followeeId);
        followee.removeFollower(followerId);

        userRepository.save(follower);
        userRepository.save(followee);
    }

    public List<User> getFollowers(String userId) {
        User user = getUserById(userId);
        return user.getFollowers().stream()
                .map(this::getUserById)
                .collect(Collectors.toList());
    }

    public List<User> getFollowing(String userId) {
        User user = getUserById(userId);
        return user.getFollowing().stream()
                .map(this::getUserById)
                .collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
