package view.filters;

import model.filters.Filter;
import model.filters.factory.FinishedFilterFactory;

import java.util.HashMap;
import java.util.Map;

public class PriorityTasksFilter implements FilterMap {
    private Map<String, Filter> filters;

    public PriorityTasksFilter() {
        filters = new HashMap<>();
        FinishedFilterFactory factory = new FinishedFilterFactory();
        filters.put("Completadas", factory.finishedTasksFilter());
        filters.put("No Completadas", factory.notFinishedTasksFilter());
        filters.put("Todas", factory.noFilter());
    }

    @Override
    public Map<String, Filter> getFilters() {
        return filters;
    }
}
