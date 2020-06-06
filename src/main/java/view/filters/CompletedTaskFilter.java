package view.filters;

import model.filters.Filter;
import model.filters.factory.FinishedFilterFactory;

import java.util.Map;
import java.util.TreeMap;

public class CompletedTaskFilter implements FilterMap {
    private Map<String, Filter> filters;

    public CompletedTaskFilter() {
        filters = new TreeMap<>();
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
