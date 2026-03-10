
package com.taskmanagement.search;

import com.taskmanagement.model.*;
import java.util.List;
import java.util.stream.Collectors;

public class PrioritySearchStrategy implements TaskSearchStrategy {

    private final Priority priority;

    public PrioritySearchStrategy(Priority priority) {
        this.priority = priority;
    }

    @Override
    public List<Task> search(List<Task> tasks) {
        return tasks.stream()
                .filter(t -> t.getPriority() == priority)
                .collect(Collectors.toList());
    }
}
