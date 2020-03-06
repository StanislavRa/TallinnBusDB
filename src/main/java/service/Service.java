package service;

import javafx.collections.ObservableList;


public interface Service<T> {

    T get(int id);

    ObservableList<T> getAll();

    boolean save(T t);

    boolean update(T t);

    boolean delete(T t);
}
