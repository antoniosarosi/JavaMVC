package view;

import controller.Controller;
import model.AskModel;
import model.filters.Filter;
import model.task.Task;
import view.table.TasksTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class View implements AskView, NotifyView {
    private Controller controller;
    private AskModel model;

    public View setController(Controller controller) {
        this.controller = controller;
        return this;
    }

    public View setModel(AskModel model) {
        this.model = model;
        return this;
    }

    private void gui() {
        JFrame window = new JFrame("Examen EI1017");
        Container container = window.getContentPane();

        // Panel filtros
        JPanel jpFilters = new JPanel();
        jpFilters.setLayout(new GridLayout(1, 3));
        JPanel jpPriority = new JPanel();
        jpPriority.setLayout(new BoxLayout(jpPriority, BoxLayout.PAGE_AXIS));
        jpPriority.setBorder(BorderFactory.createTitledBorder("Prioridad"));
        JRadioButton high = new JRadioButton("Alta");
        JRadioButton normal = new JRadioButton("Normal");
        JRadioButton low = new JRadioButton("Baja");
        JRadioButton all1 = new JRadioButton("Todas");
        ButtonGroup priorityGroup = new ButtonGroup();
        priorityGroup.add(high);
        priorityGroup.add(normal);
        priorityGroup.add(low);
        priorityGroup.add(all1);
        jpPriority.add(high);
        jpPriority.add(normal);
        jpPriority.add(low);
        jpPriority.add(all1);
        jpFilters.add(jpPriority, BorderLayout.WEST);

        JPanel jpFinished = new JPanel();
        jpFinished.setLayout(new BoxLayout(jpFinished, BoxLayout.PAGE_AXIS));
        jpFinished.setBorder(BorderFactory.createTitledBorder("Completadas"));
        JRadioButton finished = new JRadioButton("Completada");
        JRadioButton notFinished = new JRadioButton("No Completada");
        JRadioButton all2 = new JRadioButton("Todas");
        ButtonGroup finishedGroup = new ButtonGroup();
        finishedGroup.add(finished);
        finishedGroup.add(notFinished);
        finishedGroup.add(all2);
        jpFinished.add(finished);
        jpFinished.add(notFinished);
        jpFinished.add(all2);
        jpFilters.add(jpFinished, BorderLayout.CENTER);

        JPanel jpApplyFilters = new JPanel();
        JButton applyFiltersBtn = new JButton("Aplica Filtros");
        jpApplyFilters.add(applyFiltersBtn);
        jpFilters.add(jpApplyFilters);

        container.add(jpFilters, BorderLayout.NORTH);

        // Panel Tareas
        JPanel jpTasks = new JPanel();
        jpTasks.setBorder(BorderFactory.createTitledBorder("Tareas"));
        List<Task> tareas = new ArrayList<>();
        tareas.add(new Task().setTitle("Uno").setDescription("Prueba"));
        tareas.add(new Task().setTitle("Dos").setDescription("Prueba"));
        TasksTableModel tasksTableModel = new TasksTableModel(tareas);
        jpTasks.add(new JScrollPane(new JTable(new TasksTableModel(tareas))));

        container.add(jpTasks, BorderLayout.CENTER);


        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }

    public void view() {
        SwingUtilities.invokeLater(this::gui);
    }

    @Override
    public Task getTask() {
        return null;
    }

    @Override
    public List<Filter> getFilters() {
        return null;
    }
}
