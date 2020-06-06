package model.filters.factory;

import model.filters.Filter;
import model.filters.NoFilter;
import model.filters.PriorityFilter;
import model.task.Priority;

public class PriorityFilterFactory {
    public Filter lowPriorityFilter() {
        return new PriorityFilter(Priority.LOW);
    }

    public Filter normalPriorityFilter() {
        return new PriorityFilter(Priority.NORMAL);
    }

    public Filter highPriorityFilter() {
        return new PriorityFilter(Priority.HIGH);
    }

    public Filter noFilter() {
        return new NoFilter();
    }
}