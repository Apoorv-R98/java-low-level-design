
package com.taskmanagement;

import com.taskmanagement.model.*;
import com.taskmanagement.service.*;
import com.taskmanagement.observer.*;
import com.taskmanagement.search.*;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        TaskManager manager = TaskManager.getInstance();

        manager.registerObserver(new ReminderService());

        User user = new User("Apoorv","apoorv@email.com");

        Task task = manager.createTask(
                "Prepare LLD",
                "Design Task Management System",
                LocalDateTime.now().plusDays(1),
                Priority.HIGH
        );

        manager.assignTask(task.getTaskId(), user);

        manager.markTaskCompleted(task.getTaskId());

        System.out.println("Task completed successfully");

    }
}
