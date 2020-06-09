package view;

import controller.Controller;
import model.AskModel;
import model.filters.Filter;
import model.task.Task;
import view.filters.CompletedTaskFilter;
import view.filters.PriorityTaskFilter;
import view.filters.panel.FilterPanel;
import view.table.TasksTableModel;
import view.task.TaskData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class View implements AskView, NotifyView {
    private Controller controller;
    private AskModel model;
    private TaskData taskData;
    private JTable tasksTable;
    private JTextField jtfLogs;
    private List<Task> tasks;
    private List<FilterPanel> filterPanels;

    public View() {
        filterPanels = new ArrayList<>();
        tasks = new ArrayList<>();
        tasksTable = new JTable(new TasksTableModel(tasks));
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
        taskListChanged();
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
            "Completadas",
            new CompletedTaskFilter().getFilters()
        ));
        filterPanels.add(new FilterPanel(
            "Prioridad",
            new PriorityTaskFilter().getFilters()
        ));
        jpFilters.setLayout(new GridLayout(1, filterPanels.size() + 1));
        filterPanels.forEach(filterPanel -> jpFilters.add(filterPanel.getPanel()));

        JPanel jpApplyFilters = new JPanel();
        JButton applyFiltersBtn = new JButton("Aplica Filtros");
        applyFiltersBtn.addActionListener(e -> controller.changeFilters());
        jpApplyFilters.add(applyFiltersBtn);
        jpFilters.add(jpApplyFilters);

        container.add(jpFilters);

        // Panel de tareas
        JPanel jpTasks = new JPanel();
        jpTasks.setBorder(BorderFactory.createTitledBorder("Tareas"));
        JScrollPane scrollPane = new JScrollPane(tasksTable);
        scrollPane.setPreferredSize(new Dimension(500, 180));
        jpTasks.add(scrollPane);

        container.add(jpTasks);

        // Detalles de la tarea
        taskData = new TaskData();
        tasksTable.getSelectionModel().addListSelectionListener(
            e -> tasks.forEach(task -> {
                String title = tasksTable.getValueAt(
                    tasksTable.getSelectedRow(), 0
                ).toString();
                if (task.getTitle().equals(title)) {
                    taskData.updateViewData(task);
                }
            })
        );
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

        // Logs
        JPanel jpLogs = new JPanel();
        jtfLogs = new JTextField(40);
        jpLogs.setBorder(BorderFactory.createTitledBorder("Última Acción"));
        jtfLogs.setEditable(false);
        jpLogs.add(jtfLogs);

        container.add(jpLogs);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.store();
                System.exit(0);
            }
        });
        window.pack();
        window.setVisible(true);
    }

    /**
     * Arranca la interfaz gráfica
     */
    public void view() {
        SwingUtilities.invokeLater(this::gui);
    }

    /**
     * Devuelve los datos de la tarea escritos por el usuario
     *
     * @return Tarea
     */
    @Override
    public Task getTask() {
        return new Task()
            .setTitle(taskData.getTitle())
            .setDescription(taskData.getDescription())
            .setFinished(taskData.getCompleted())
            .setPriority(taskData.getPriority());
    }

    /**
     * Devuelve los filtros aplicados por el usuario
     *
     * @return Lista de filtros
     */
    @Override
    public List<Filter> getFilters() {
        return filterPanels.stream()
            .map(FilterPanel::getFilter)
            .collect(Collectors.toList());
    }

    /**
     * Imprime un mensaje en la interfaz
     *
     * @param msg Mensaje
     */
    @Override
    public void log(String msg) {
        jtfLogs.setText(msg);
    }

    /**
     * Notifica al usuario de que ha ocurrido un error
     *
     * @param err Mensaje de error
     */
    @Override
    public void error(String err) {
        JOptionPane.showMessageDialog(new Frame(), err);
    }

    /**
     * Informa a la vista de que los datos han cambiado y los actualiza
     */
    @Override
    public void taskListChanged() {
        tasks = model.getTasks();
        tasksTable.setModel(new TasksTableModel(tasks));
    }
}
