package model.filters.factory;

import model.filters.Filter;
import model.filters.FinishedFilter;
import model.filters.NoFilter;

/**
 * Fábrica para filtrar por tareas completadas
 */
public class FinishedFilterFactory {
    public Filter finishedTasksFilter() {
        return new FinishedFilter(true);
    }

    public Filter notFinishedTasksFilter() {
        return new FinishedFilter(false);
    }

    public Filter noFilter() {
        return new NoFilter();
    }
}
