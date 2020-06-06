package controller;

import model.ModelChanges;
import view.AskView;

public class TodoListController implements Controller {
    private AskView view;
    private ModelChanges model;

    public TodoListController() {
    }

    public TodoListController setView(AskView view) {
        this.view = view;
        return this;
    }

    public TodoListController setModel(ModelChanges model) {
        this.model = model;
        return this;
    }

    @Override
    public void addTask() {
        model.addTask(view.getTask());
    }

    @Override
    public void removeTask() {
        model.removeTask(view.getTask());
    }

    @Override
    public void updateTask() {
        model.updateTask(view.getTask());
    }

    @Override
    public void changeFilters() {
        model.changeFilters(view.getFilters());
    }
}
