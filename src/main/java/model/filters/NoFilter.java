package model.filters;

import model.task.Task;

import java.util.List;

public class NoFilter extends Filter {
    @Override
    public void filter(List<Task> tasks) {
        // No aplica ningún filtro
    }
}
