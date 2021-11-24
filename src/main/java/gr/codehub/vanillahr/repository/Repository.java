package gr.codehub.vanillahr.repository;

import gr.codehub.vanillahr.model.Employee;

import java.util.List;
import java.util.Optional;

/**
 * Repository gives the signatures of the methods that
 * communicate with the database
 *
 */
public interface Repository<T> {
    //CRUD

    T create (T t);
    Optional<T> read(int id);
    List<T> read();
    T update(int Id, T e);
    boolean delete(int Id);
}
