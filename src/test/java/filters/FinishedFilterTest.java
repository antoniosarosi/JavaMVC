package filters;

import model.filters.FinishedFilter;
import model.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class FinishedFilterTest {
    private List<Task> tasks; // Todas las tareas
    private List<Task> expected; // Solo tareas completadas
    private FinishedFilter filter;

    @BeforeEach
    void setUp() {
        filter = new FinishedFilter(true);
        tasks = new ArrayList<>();
        expected = new ArrayList<>();

        int notFinishedTasks = 3;
        int finishedTasks = 2;

        for (int i = 0; i < notFinishedTasks; i++) {
            tasks.add(new Task().setFinished(false));
        }

        for (int i = notFinishedTasks; i < notFinishedTasks + finishedTasks; i++) {
            Task task = new Task().setFinished(true);
            tasks.add(task);
            expected.add(task);
        }
    }

    @Test
    void filterTest() {
        filter.filter(tasks);
        assertEquals(expected, tasks);
    }
}