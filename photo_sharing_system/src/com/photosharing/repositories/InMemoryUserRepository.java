package com.photosharing.repositories;

import com.photosharing.models.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users;

    public InMemoryUserRepository() {
        this.users = new ConcurrentHashMap<>();
    }

    @Override
    public User save(User user) {
        users.put(user.getUserId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public boolean deleteById(String userId) {
        return users.remove(userId) != null;
    }

    @Override
    public boolean existsById(String userId) {
        return users.containsKey(userId);
    }
}

