package edu.aitu.oop3.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    void add(T item);

    List<T> getAll();

    Optional<T> findById(String id);

    boolean removeById(String id);
}
