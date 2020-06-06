package view.table;

import model.task.Task;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.List;

/**
 * Tabla de tareas
 */
public class TasksTableModel extends AbstractTableModel {
    private List<String> columns;
    private List<Task> tasks;

    public TasksTableModel(List<Task> tasks) {
        columns = Arrays.asList("Titulo", "Prioridad", "Completada");
        this.tasks = tasks;
    }

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = tasks.get(0);
        if (rowIndex >= 0) {
            task = tasks.get(rowIndex);
        }
        switch (columnIndex) {
            case 0: return task.getTitle();
            case 1: return task.getPriority().toString();
            case 2: return task.isFinished() ? "SI" : "NO";
            default: return task;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(column);
    }
}
