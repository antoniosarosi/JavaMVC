package model.filters;

import model.task.Task;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Filter {
    protected void filter(List<Task> tasks, Predicate<Task> filter) {
        tasks.removeIf(filter);
    }

    public abstract void filter(List<Task> tasks);
}
