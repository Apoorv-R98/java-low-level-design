
package com.taskmanagement.service;

import com.taskmanagement.model.*;
import com.taskmanagement.repository.*;
import com.taskmanagement.repository.impl.*;
import com.taskmanagement.factory.*;
import com.taskmanagement.search.*;
import com.taskmanagement.observer.*;
import com.taskmanagement.exception.*;

import java.time.LocalDateTime;
import java.util.*;

public class TaskManager {

    private static final TaskManager INSTANCE = new TaskManager();

    private final TaskRepository repository = new InMemoryTaskRepository();
    private final List<TaskObserver> observers = new ArrayList<>();

    private TaskManager(){}

    public static TaskManager getInstance(){
        return INSTANCE;
    }

    public void registerObserver(TaskObserver observer){
        observers.add(observer);
    }

    public Task createTask(String title, String description,
                           LocalDateTime dueDate, Priority priority){

        if(title == null || title.isEmpty()){
            throw new InvalidTaskOperationException("Task title cannot be empty");
        }

        Task task = TaskFactory.createTask(title, description, dueDate, priority);
        repository.save(task);
        return task;
    }

    public void assignTask(String taskId, User user){
        Task task = repository.findById(taskId);

        if(task.getStatus() == TaskStatus.COMPLETED){
            throw new InvalidTaskOperationException("Cannot assign completed task");
        }

        task.assignUser(user);
    }

    public void markTaskCompleted(String taskId){
        Task task = repository.findById(taskId);

        if(task.getStatus() == TaskStatus.COMPLETED){
            throw new InvalidTaskOperationException("Task already completed");
        }

        task.markCompleted();
    }

    public void deleteTask(String taskId){
        repository.delete(taskId);
    }

    public List<Task> searchTasks(TaskSearchStrategy strategy){
        return strategy.search(new ArrayList<>(repository.findAll()));
    }

    public void checkDueTasks(){

        for(Task task : repository.findAll()){

            if(task.getDueDate().isBefore(LocalDateTime.now())){

                for(TaskObserver observer : observers){
                    observer.onTaskDue(task);
                }

            }

        }

    }
}
