package model;

import model.filters.Filter;
import model.task.Task;

import java.util.Collection;

public interface ModelChanges {
    boolean addTask(Task task);

    boolean removeTask(Task task);

    boolean updateTask(Task task);

    void changeFilters(Collection<Filter> filters);

    void store();
}
