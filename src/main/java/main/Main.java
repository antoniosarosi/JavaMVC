package main;

import controller.TodoListController;
import model.Model;
import view.View;

public class Main {
    public static void main(String[] args) {
        Model model = new Model().load();
        View view = new View();
        TodoListController controller = new TodoListController();
        model.setView(view);
        controller.setView(view).setModel(model);
        view.setModel(model).setController(controller);

        view.view();
    }
}
