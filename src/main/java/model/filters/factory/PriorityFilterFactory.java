package model.filters.factory;

import model.filters.Filter;
import model.filters.NoFilter;
import model.filters.PriorityFilter;
import model.task.Priority;

/**
 * Fábrica para filtrar por prioridad
 */
public class PriorityFilterFactory {
    public Filter lowPriorityFilter() {
        return new PriorityFilter(Priority.BAJA);
    }

    public Filter normalPriorityFilter() {
        return new PriorityFilter(Priority.NORMAL);
    }

    public Filter highPriorityFilter() {
        return new PriorityFilter(Priority.ALTA);
    }

    public Filter noFilter() {
        return new NoFilter();
    }
}
