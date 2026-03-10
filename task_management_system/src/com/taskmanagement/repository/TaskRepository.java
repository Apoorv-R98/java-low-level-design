
package com.taskmanagement.repository;

import com.taskmanagement.model.Task;
import java.util.Collection;

public interface TaskRepository {

    void save(Task task);

    Task findById(String taskId);

    void delete(String taskId);

    Collection<Task> findAll();
}
