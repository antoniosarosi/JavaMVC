package model;


import model.filters.Filter;
import model.task.Task;
import view.NotifyView;

import java.util.ArrayList;
import java.util.List;

public class Model implements AskModel, ModelChanges {
    private NotifyView view;
    private List<Task> tasks;
    private List<Filter> filters;

    public Model() {
        tasks = new ArrayList<>();
    }

    public Model setView(NotifyView view) {
        this.view = view;
        return this;
    }

    @Override
    public void setFilters(List<Filter> filters) {
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
    }

    @Override
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    @Override
    public void updateTask(Task task) {
        tasks.set(tasks.indexOf(task), task);
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
