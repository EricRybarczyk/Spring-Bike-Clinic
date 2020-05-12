package dev.ericrybarczyk.springbikeclinic.services;

import java.util.Set;

/*
    This interface mimics the Repository interface in Spring Data, in a simplistic way for now.
 */
public interface CrudService<T, ID> {
    Set<T> findAll();
    T findById(ID id);
    T save(T object);
    void delete(T object);
    void deleteById(ID id);
}
