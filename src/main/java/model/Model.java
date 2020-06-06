package model;


import model.filters.Filter;
import model.task.Task;
import view.NotifyView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Model implements AskModel, ModelChanges {
    private NotifyView view;
    private List<Task> tasks;
    private Collection<Filter> filters;

    public Model() {
        tasks = new ArrayList<>();
    }

    public Model setView(NotifyView view) {
        this.view = view;
        return this;
    }

    public void setFilters(Collection<Filter> filters) {
        this.filters = filters;
    }

    @Override
    public List<Task> getTasks() {
        List<Task> list = new ArrayList<>(tasks);
        filters.forEach(filter -> filter.filter(list));

        return list;
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
        view.taskListChanged();
    }

    @Override
    public void removeTask(Task task) {
        tasks.remove(task);
        view.taskListChanged();
    }

    @Override
    public void updateTask(Task task) {
        tasks.set(tasks.indexOf(task), task);
        view.taskListChanged();
    }

    @Override
    public void changeFilters(Collection<Filter> filters) {
        setFilters(filters);
    }

    /**
     * Carga los datos almacenados en disco
     *
     * @return este objeto
     */
    public Model load() {
        return this;
    }

    /**
     * Almacena los datos en disco
     */
    public void store() {

    }
}
