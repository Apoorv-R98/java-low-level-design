
package com.taskmanagement.search;

import com.taskmanagement.model.Task;
import java.util.List;

public interface TaskSearchStrategy {
    List<Task> search(List<Task> tasks);
}
