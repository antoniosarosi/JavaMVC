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

    public Model() {
        tasks = new ArrayList<>();
        filters = new ArrayList<>();
    }

    public Model setView(NotifyView view) {
        this.view = view;
        return this;
    }

    public void setFilters(Collection<Filter> filters) {
        this.filters = filters;
    }

    @Override
    public List<Task> getTasks() {
        List<Task> list = new ArrayList<>(tasks);
        filters.forEach(filter -> filter.filter(list));

        return list;
    }

    @Override
    public boolean addTask(Task task) {
        if (tasks.contains(task)) {
            return false;
        }
        tasks.add(task);
        view.taskListChanged();

        return true;
    }

    @Override
    public boolean removeTask(Task task) {
        if (!tasks.contains(task)) {
            return false;
        }
        tasks.remove(task);
        view.taskListChanged();

        return true;
    }

    @Override
    public boolean updateTask(Task task) {
        if (!tasks.contains(task)) {
            return false;
        }
        tasks.set(tasks.indexOf(task), task);
        view.taskListChanged();

        return true;
    }

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
    public Model load() {
        try {
            FileInputStream fis = new FileInputStream("datos.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            tasks = (ArrayList) ois.readObject();
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
            FileOutputStream fos = new FileOutputStream("datos.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
            System.out.println("Datos guardados con éxito");
        }
        catch (IOException e)  {
            System.err.println("No se han podido almacenar los datos");
        }
    }
}
