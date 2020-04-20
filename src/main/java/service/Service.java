package service;


import db.DatabaseHandler;
import javafx.collections.ObservableList;

import java.sql.Connection;


public interface Service<T> {

    Connection connection = DatabaseHandler.getInstance().getConnection();

    T get(Long id);

    ObservableList<T> getAll();

    boolean save(T t);

    boolean update(T t);

    boolean delete(T t);
}
