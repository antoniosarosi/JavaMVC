package model;

import model.filters.Filter;
import model.task.Task;

import java.util.List;

public interface ModelChanges {
    void addTask(Task task);

    void removeTask(Task task);

    void updateTask(Task task);

    void setFilters(List<Filter> filters);
}
