package view.task;

import model.task.Priority;
import model.task.Task;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Se encarga de actualizar los detalles de la tarea cada vez que sea necesario
 */
public class TaskData {
    private JTextField title;
    private JTextArea description;
    private JCheckBox completed;
    private List<JRadioButton> buttons;
    private Map<String, Priority> priority; // Texto Botón -> Prioridad

    public TaskData() {
        title = new JTextField(40);

        description = new JTextArea();
        description.setColumns(37);
        description.setRows(5);
        description.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);

        completed = new JCheckBox("Completada");

        buttons = new ArrayList<>();
        priority = new HashMap<>();
        JRadioButton lowPriorityBtn = new JRadioButton("Baja");
        priority.put(lowPriorityBtn.getText(), Priority.BAJA);
        buttons.add(lowPriorityBtn);
        JRadioButton normalPriorityBtn = new JRadioButton("Normal");
        priority.put(normalPriorityBtn.getText(), Priority.NORMAL);
        buttons.add(normalPriorityBtn);
        JRadioButton highPriorityBtn = new JRadioButton("Alta");
        priority.put(highPriorityBtn.getText(), Priority.ALTA);
        buttons.add(highPriorityBtn);
    }

    /**
     * Actualiza los campos de texto y botones con los datos de la tarea que corresponde
     *
     * @param task Tarea
     */
    public void updateViewData(Task task) {
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        completed.setSelected(task.isFinished());
        buttons.forEach(btn -> btn.setSelected(
            priority.get(btn.getText()) == task.getPriority()
        ));
    }

    /**
     * Crea el componente correspondiente a los detalles de la tarea
     *
     * @return Panel de detalles de la tarea
     */
    public JPanel getPanel() {
        // Panel
        JPanel jpDetails = new JPanel();
        jpDetails.setBorder(BorderFactory.createTitledBorder("Detalles de la tarea"));
        jpDetails.setLayout(new BoxLayout(jpDetails, BoxLayout.PAGE_AXIS));

        // Título
        JPanel jpTitle = new JPanel();
        jpTitle.add(new JLabel("Título"));
        jpTitle.add(title);
        jpDetails.add(jpTitle);

        // Descripción
        JPanel jpDescription = new JPanel();
        jpDescription.add(new JLabel("Descripción"));

        jpDescription.add(description);
        jpDetails.add(jpDescription);

        // Checkbox completada
        jpDetails.add(completed);

        // Prioridad
        JPanel jpPriority = new JPanel();
        jpPriority.setBorder(BorderFactory.createTitledBorder("Prioridad"));
        ButtonGroup priorityBtns = new ButtonGroup();

        buttons.forEach(btn -> {
            priorityBtns.add(btn);
            jpPriority.add(btn);
        });

        jpDetails.add(jpPriority);

        return jpDetails;
    }

    /**
     * @return Título de la tarea actual
     */
    public String getTitle() {
        return title.getText();
    }

    /**
     * @return Descripción de la tarea actual
     */
    public String getDescription() {
        return description.getText();
    }

    /**
     * @return Si la tarea actual está acabada o no
     */
    public boolean getCompleted() {
        return completed.isSelected();
    }

    public Priority getPriority() {
        for (JRadioButton btn : buttons) {
            if (btn.isSelected()) {
                return priority.get(btn.getText());
            }
        }
        return Priority.BAJA;
    }
}
