package view;

import controller.Controller;
import model.AskModel;
import model.filters.Filter;
import model.task.Task;
import view.filters.CompletedTasksFilter;
import view.filters.PriorityTasksFilter;
import view.filters.panel.FilterPanel;
import view.table.TasksTableModel;
import view.task.TaskData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class View implements AskView, NotifyView {
    private Controller controller;
    private AskModel model;
    TaskData taskData;
    List<Task> tasks;
    List<FilterPanel> filterPanels;

    public View() {
        filterPanels = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    /**
     * @param controller Controlador
     */
    public View setController(Controller controller) {
        this.controller = controller;
        return this;
    }

    /**
     * @param model Modelo
     */
    public View setModel(AskModel model) {
        this.model = model;
        return this;
    }

    /**
     * Crea la interfaz gráfica
     */
    private void gui() {
        JFrame window = new JFrame("Tareas EI1017");
        Container container = window.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        // Paneles de filtros
        JPanel jpFilters = new JPanel();
        filterPanels.add(new FilterPanel(
            "Prioridad",
            new PriorityTasksFilter().getFilters()
        ));
        filterPanels.add(new FilterPanel(
            "Completadas",
            new CompletedTasksFilter().getFilters()
        ));
        jpFilters.setLayout(new GridLayout(1, filterPanels.size() + 1));
        filterPanels.forEach(filterPanel -> jpFilters.add(filterPanel.getPanel()));

        JPanel jpApplyFilters = new JPanel();
        JButton applyFiltersBtn = new JButton("Aplica Filtros");
        jpApplyFilters.add(applyFiltersBtn);
        jpFilters.add(jpApplyFilters);

        container.add(jpFilters);

        // Panel de tareas
        JPanel jpTasks = new JPanel();
        jpTasks.setBorder(BorderFactory.createTitledBorder("Tareas"));
        JScrollPane tasksTable = new JScrollPane(new JTable(new TasksTableModel(tasks)));
        tasksTable.setPreferredSize(new Dimension(500, 200));
        jpTasks.add(tasksTable);

        container.add(jpTasks);

        // Detalles de la tarea
        taskData = new TaskData();
        container.add(taskData.getPanel());

        // Acción
        JPanel jpAction = new JPanel();
        JButton newTask = new JButton("Nuevo");
        newTask.addActionListener(e -> controller.addTask());
        jpAction.add(newTask);
        JButton updateTask = new JButton("Actualiza");
        updateTask.addActionListener(e -> controller.updateTask());
        jpAction.add(updateTask);
        JButton removeTask = new JButton("Borra");
        removeTask.addActionListener(e -> controller.removeTask());
        jpAction.add(removeTask);

        container.add(jpAction);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }

    public void view() {
        SwingUtilities.invokeLater(this::gui);
    }

    @Override
    public Task getTask() {
        return new Task()
            .setTitle(taskData.getTitle())
            .setDescription(taskData.getDescription())
            .setFinished(taskData.getCompleted())
            .setPriority(taskData.getPriority());
    }

    @Override
    public List<Filter> getFilters() {
        return filterPanels.stream()
            .map(FilterPanel::getFilter)
            .collect(Collectors.toList());
    }

    @Override
    public void taskListChanged() {
        tasks = model.getTasks();
    }
}
