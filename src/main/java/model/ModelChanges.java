package model;

import model.filters.Filter;
import model.task.Task;

import java.util.Collection;

public interface ModelChanges {
    void addTask(Task task);

    void removeTask(Task task);

    void updateTask(Task task);

    void changeFilters(Collection<Filter> filters);
}
