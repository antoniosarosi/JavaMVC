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
        if (!model.addTask(view.getTask())) {
            view.error("Ya existe una tarea con ese título");
        }
    }

    @Override
    public void removeTask() {
        if (!model.removeTask(view.getTask())) {
            view.error("No existe ninguna tarea con ese título");
        }
    }

    @Override
    public void updateTask() {
        if (!model.updateTask(view.getTask())) {
            view.error("No existe ninguna tarea con ese título");
        }
    }

    @Override
    public void changeFilters() {
        model.changeFilters(view.getFilters());
    }

    @Override
    public void store() {
        model.store();
    }
}
