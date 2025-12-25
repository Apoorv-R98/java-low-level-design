package com.photosharing.repositories;

import com.photosharing.models.User;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User data access operations
 */
public interface UserRepository {

    User save(User user);

    Optional<User> findById(String userId);

    List<User> findAll();

    boolean deleteById(String userId);

    boolean existsById(String userId);
}

