
package com.taskmanagement.observer;

import com.taskmanagement.model.Task;

public interface TaskObserver {
    void onTaskDue(Task task);
}
