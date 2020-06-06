package view.filters;

import model.filters.Filter;
import model.filters.factory.PriorityFilterFactory;

import java.util.Map;
import java.util.TreeMap;

public class PriorityTaskFilter implements FilterMap {
    private Map<String, Filter> filters;

    public PriorityTaskFilter() {
        PriorityFilterFactory factory = new PriorityFilterFactory();
        filters = new TreeMap<>();
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
