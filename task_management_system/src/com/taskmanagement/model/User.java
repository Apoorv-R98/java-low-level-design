
package com.taskmanagement.model;

import java.util.UUID;

public class User {

    private final String userId;
    private String name;
    private String email;

    public User(String name, String email) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
