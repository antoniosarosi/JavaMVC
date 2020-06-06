package view.filters.panel;

import model.filters.Filter;
import model.filters.NoFilter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cada panel de filtros actualiza su estado cuando el usuario elige el filtro
 * que quiere usando los JRadioButton, y después la vista solo tiene que mantener
 * una lista de paneles, a partir de ella es muy fácil construir los filtros
 * que recibe el modelo cuando el usuario quiera aplicarlos.
 */
public class FilterPanel {
    private String title;
    private Map<String, Filter> filterMap; // Texto Botón -> Filtro
    private List<JRadioButton> buttons;

    public FilterPanel() {
        title = "Titulo";
        filterMap = new HashMap<>();
        buttons = new ArrayList<>();
    }

    /**
     * @param title Titulo del panel
     * @param filterMap Mapa con los títulos de los botones y filtro de cada uno
     */
    public FilterPanel(String title, Map<String, Filter> filterMap) {
        this.title = title;
        this.filterMap = filterMap;
        buttons = new ArrayList<>();
    }

    /**
     * Crea un panel con un grupo JRadioButton a partir del mapa que recibe
     * en el constructor
     *
     * @return Panel de botones
     */
    public JPanel getPanel() {
        JPanel jpFilterPanel = new JPanel();
        jpFilterPanel.setLayout(new BoxLayout(jpFilterPanel, BoxLayout.PAGE_AXIS));
        jpFilterPanel.setBorder(BorderFactory.createTitledBorder(title));
        ButtonGroup btnGroup = new ButtonGroup();

        for (String btnText : filterMap.keySet()) {
            JRadioButton btn = new JRadioButton(btnText);
            btnGroup.add(btn);
            jpFilterPanel.add(btn);
            buttons.add(btn);
        }

        return jpFilterPanel;
    }

    /**
     * @return Filtro elegido por el usuario
     */
    public Filter getFilter() {
        for (JRadioButton btn : buttons) {
            if (btn.isSelected()) {
                return filterMap.get(btn.getText());
            }
        }
        return new NoFilter();
    }

    // Se considera que el título identifica al panel
    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        if (this == o) {
            return true;
        }
        return this.title.equals(((FilterPanel) o).title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
