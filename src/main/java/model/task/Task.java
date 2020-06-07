package model.task;

import java.io.Serializable;

/**
 * Cada una de las tareas que almacenará el modelo, se considera que el título es único
 */
public class Task implements Serializable {
    private String title;
    private String description;
    private Priority priority;
    private boolean finished;

    public Task() {
        title = "Titulo";
        description = "Descripcion";
        priority = Priority.BAJA;
        finished = false;
    }

    public String getTitle() {
        return title;
    }

    public Task setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public Priority getPriority() {
        return priority;
    }

    public Task setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public boolean isFinished() {
        return finished;
    }

    public Task setFinished(boolean finished) {
        this.finished = finished;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        if (this == o) {
            return true;
        }
        return this.title.equals(((Task) o).title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
