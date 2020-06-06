package filters;

import model.filters.PriorityFilter;
import model.task.Priority;
import model.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PriorityFilterTest {
    private List<Task> tasks;
    private List<Task> expected;
    private PriorityFilter filter;
    Priority filterBy;

    @BeforeEach
    void setUp() {
        filterBy = Priority.ALTA;
        filter = new PriorityFilter(filterBy);
        tasks = new ArrayList<>();
        expected = new ArrayList<>();

        addTasks(Priority.BAJA);
        addTasks(Priority.ALTA);
        addTasks(Priority.NORMAL);
    }

    void addTasks(Priority priority) {
        for (int i = 0; i < 3; i++) {
            Task task = new Task().setPriority(priority);
            tasks.add(task);
            if (task.getPriority() == filterBy) {
                expected.add(task);
            }
        }
    }

    @Test
    void filterTest() {
        filter.filter(tasks);
        assertEquals(expected, tasks);
    }
}
