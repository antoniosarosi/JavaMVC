package controller;

import model.ModelChanges;
import model.task.Task;
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
        Task task = view.getTask();
        if (task.getTitle().isEmpty()) {
            view.error("El título de la tarea no puede estar vacío");
        } else if (!model.addTask(task)) {
            view.error("Ya existe una tarea con ese título");
        } else {
            view.log("Tarea añadida");
        }
    }

    @Override
    public void removeTask() {
        if (!model.removeTask(view.getTask())) {
            view.error("No existe ninguna tarea con ese título");
        } else {
            view.log("Tarea borrada");
        }
    }

    @Override
    public void updateTask() {
        if (!model.updateTask(view.getTask())) {
            view.error("No existe ninguna tarea con ese título");
        } else {
            view.log("Tarea actualizada");
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
