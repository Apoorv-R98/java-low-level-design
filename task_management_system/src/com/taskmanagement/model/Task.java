
package com.taskmanagement.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {

    private final String taskId;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Priority priority;
    private TaskStatus status;
    private User assignedUser;

    public Task(String title, String description, LocalDateTime dueDate, Priority priority) {
        this.taskId = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = TaskStatus.PENDING;
    }

    public String getTaskId() {
        return taskId;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void assignUser(User user) {
        this.assignedUser = user;
    }

    public void markCompleted() {
        this.status = TaskStatus.COMPLETED;
    }

    public TaskStatus getStatus() {
        return status;
    }
}
