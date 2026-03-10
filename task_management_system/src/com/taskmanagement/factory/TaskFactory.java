
package com.taskmanagement.factory;

import com.taskmanagement.model.*;
import java.time.LocalDateTime;

public class TaskFactory {

    public static Task createTask(String title, String description,
                                  LocalDateTime dueDate, Priority priority) {

        return new Task(title, description, dueDate, priority);
    }
}
