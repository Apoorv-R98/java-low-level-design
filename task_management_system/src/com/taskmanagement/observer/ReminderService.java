
package com.taskmanagement.observer;

import com.taskmanagement.model.Task;

public class ReminderService implements TaskObserver {

    @Override
    public void onTaskDue(Task task) {
        System.out.println("Reminder: Task due -> " + task.getTaskId());
    }
}
