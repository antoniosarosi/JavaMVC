package model.filters;

import model.task.Priority;
import model.task.Task;

import java.util.List;

public class PriorityFilter extends Filter {
    private Priority priority;

    public PriorityFilter(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void filter(List<Task> tasks) {
        super.filter(tasks, task -> task.getPriority() == priority);
    }
}
