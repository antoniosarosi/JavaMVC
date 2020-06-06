package model.filters;

import model.task.Task;

import java.util.List;

/**
 * Filtro vacío, no aplica ningún tipo de filtro
 */
public class NoFilter extends Filter {
    @Override
    public void filter(List<Task> tasks) {
        // No hace nada
    }
}
