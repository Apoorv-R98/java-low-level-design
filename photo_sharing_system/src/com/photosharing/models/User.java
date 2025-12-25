package com.photosharing.models;

import java.util.HashSet;
import java.util.Set;

public class User {
    private final String userId;
    private final String username;
    private final Set<String> followers;    
    private final Set<String> following;   

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.followers = new HashSet<>();
        this.following = new HashSet<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getFollowers() {
        return new HashSet<>(followers);
    }

    public Set<String> getFollowing() {
        return new HashSet<>(following);
    }

    public void addFollower(String followerId) {
        followers.add(followerId);
    }

    public void removeFollower(String followerId) {
        followers.remove(followerId);
    }

    public void addFollowing(String followeeId) {
        following.add(followeeId);
    }

    public void removeFollowing(String followeeId) {
        following.remove(followeeId);
    }

    public boolean isFollowing(String userId) {
        return following.contains(userId);
    }

    public boolean isFollowedBy(String userId) {
        return followers.contains(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", followers=" + followers.size() +
                ", following=" + following.size() +
                '}';
    }
}

