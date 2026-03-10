
package com.taskmanagement.repository.impl;

import com.taskmanagement.repository.TaskRepository;
import com.taskmanagement.model.Task;
import com.taskmanagement.exception.TaskNotFoundException;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTaskRepository implements TaskRepository {

    private final ConcurrentHashMap<String, Task> storage = new ConcurrentHashMap<>();

    @Override
    public void save(Task task) {
        storage.put(task.getTaskId(), task);
    }

    @Override
    public Task findById(String taskId) {
        Task task = storage.get(taskId);
        if (task == null) {
            throw new TaskNotFoundException("Task not found: " + taskId);
        }
        return task;
    }

    @Override
    public void delete(String taskId) {
        if (storage.remove(taskId) == null) {
            throw new TaskNotFoundException("Cannot delete. Task not found: " + taskId);
        }
    }

    @Override
    public Collection<Task> findAll() {
        return storage.values();
    }
}
