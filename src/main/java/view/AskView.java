package view;

import model.filters.Filter;
import model.task.Task;

import java.util.List;

public interface AskView {
    Task getTask();

    List<Filter> getFilters();

    void error(String err);

    void log(String msg);
}
