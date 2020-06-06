package view.filters;

import model.filters.Filter;
import model.filters.factory.PriorityFilterFactory;

import java.util.HashMap;
import java.util.Map;

public class CompletedTasksFilter implements FilterMap {
    private Map<String, Filter> filters;

    public CompletedTasksFilter() {
        PriorityFilterFactory factory = new PriorityFilterFactory();
        filters = new HashMap<>();
        filters.put("Alta", factory.highPriorityFilter());
        filters.put("Normal", factory.normalPriorityFilter());
        filters.put("Baja", factory.lowPriorityFilter());
        filters.put("Todas", factory.noFilter());
    }

    @Override
    public Map<String, Filter> getFilters() {
        return filters;
    }
}
