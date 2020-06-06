package view.filters;

import model.filters.Filter;

import java.util.Map;

/**
 * Asocia el texto de un botón con el filtro que aplica
 */
public interface FilterMap {
    Map<String, Filter> getFilters();
}
