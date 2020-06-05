package model.filters;

import model.task.Task;

import java.util.List;

public class FinishedFilter extends Filter {
    private boolean finished;

    public FinishedFilter(boolean finished) {
        this.finished = finished;
    }

    @Override
    public void filter(List<Task> tasks) {
        super.filter(tasks, task -> task.isFinished() == finished);
    }
}
