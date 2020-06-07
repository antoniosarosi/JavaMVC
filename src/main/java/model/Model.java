package model;

import model.filters.Filter;
import model.task.Task;
import view.NotifyView;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Model implements AskModel, ModelChanges {
    private NotifyView view;
    private List<Task> tasks;
    private Collection<Filter> filters;
    private String data;

    public Model() {
        tasks = new ArrayList<>();
        filters = new ArrayList<>();
        data = "datos.bin";
    }

    public Model setView(NotifyView view) {
        this.view = view;
        return this;
    }

    public void setFilters(Collection<Filter> filters) {
        this.filters = filters;
    }

    /**
     * Devuelve la lista de tareas, aplicando los filtros correspondientes
     *
     * @return Lista de tareas
     */
    @Override
    public List<Task> getTasks() {
        List<Task> list = new ArrayList<>(tasks);
        filters.forEach(filter -> filter.filter(list));

        return list;
    }

    /**
     * Añade una tarea nueva
     *
     * @param task Tarea a añadir
     * @return true si se ha añadido, false en caso contrario (ya existía)
     */
    @Override
    public boolean addTask(Task task) {
        if (tasks.contains(task)) {
            return false;
        }
        tasks.add(task);
        view.taskListChanged();

        return true;
    }

    /**
     * Borra una tarea
     *
     * @param task Tarea a borrar
     * @return true si la ha borrado, false en otro caso (no existe la tarea)
     */
    @Override
    public boolean removeTask(Task task) {
        int index = tasks.indexOf(task);
        if (index < 0) {
            return false;
        }
        tasks.remove(index);
        view.taskListChanged();

        return true;
    }

    /**
     * Actualiza una tarea con los datos nuevos
     *
     * @param task Tarea a actualizar
     * @return true si se ha actualizado, false si la tarea no existe
     */
    @Override
    public boolean updateTask(Task task) {
        int index = tasks.indexOf(task);
        if (index < 0) {
            return false;
        }
        tasks.set(index, task);
        view.taskListChanged();

        return true;
    }

    /**
     * Cambia los filtros que se aplican
     *
     * @param filters Filtros a aplicar
     */
    @Override
    public void changeFilters(Collection<Filter> filters) {
        setFilters(filters);
        view.taskListChanged();
    }

    /**
     * Carga los datos almacenados en disco
     *
     * @return este objeto
     */
    @SuppressWarnings("unchecked")
    public Model load() {
        try {
            FileInputStream fis = new FileInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tasks = (ArrayList<Task>) ois.readObject();
            ois.close();
            System.out.println("Datos cargados con éxito");
        }
        catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero de datos");
        }
        catch (IOException e) {
            System.err.println("Error de entrada / salida al cargar datos");
        }
        catch (ClassNotFoundException e) {
            System.err.println("El fichero no contiene datos correctos");
        }

        return this;
    }

    /**
     * Almacena los datos en disco
     */
    @Override
    public void store() {
        try {
            FileOutputStream fos = new FileOutputStream(data);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
            System.out.println("Datos guardados con éxito");
        }
        catch (IOException e) {
            System.err.println("No se han podido almacenar los datos");
        }
    }
}
