package view.task;

import model.task.Priority;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TaskData {

    private JTextField title;
    private JTextArea description;
    private JCheckBox completed;
    private Map<JRadioButton, Priority> priority;

    public TaskData() {
        title = new JTextField(40);

        description = new JTextArea();
        description.setColumns(37);
        description.setRows(5);
        description.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);

        completed = new JCheckBox("Completada");

        priority = new HashMap<>();
        JRadioButton lowPriorityBtn = new JRadioButton("Baja");
        priority.put(lowPriorityBtn, Priority.LOW);
        JRadioButton normalPriorityBtn = new JRadioButton("Normal");
        priority.put(normalPriorityBtn, Priority.NORMAL);
        JRadioButton highPriorityBtn = new JRadioButton("Alta");
        priority.put(highPriorityBtn, Priority.HIGH);
    }

    public JPanel getPanel() {
        // Panel de detalles de la tarea
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

        for (JRadioButton btn : priority.keySet()) {
            priorityBtns.add(btn);
            jpPriority.add(btn);

        }
        jpDetails.add(jpPriority);

        return jpDetails;
    }

    public String getTitle() {
        return title.getText();
    }

    public String getDescription() {
        return description.getText();
    }

    public boolean getCompleted() {
        return completed.isSelected();
    }

    public Priority getPriority() {
        for (JRadioButton btn : priority.keySet()) {
            if (btn.isSelected()) {
                return priority.get(btn);
            }
        }
        return Priority.LOW;
    }
}
