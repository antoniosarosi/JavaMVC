package model.filters;

import model.task.Task;

import java.util.List;

/**
 * Filtra la lista por tareas acabadas o no acabadas
 */
public class FinishedFilter extends Filter {
    private boolean finished;

    /**
     * @param finished Filtrar por tareas acabadas (true) o no acabadas (false)
     */
    public FinishedFilter(boolean finished) {
        this.finished = finished;
    }

    @Override
    public void filter(List<Task> tasks) {
        super.filter(tasks, task -> task.isFinished() != finished);
    }
}
