package edu.aitu.oop3.repository;

import java.util.*;
import java.util.function.Function;

public class InMemoryRepository<T> implements Repository<T> {

    private Map<String, T> storage = new HashMap<>();
    private Function<T, String> idExtractor;

    public InMemoryRepository(Function<T, String> idExtractor) {
        this.idExtractor = idExtractor;
    }

    @Override
    public void add(T item) {
        storage.put(idExtractor.apply(item), item);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public boolean removeById(String id) {
        return storage.remove(id) != null;
    }
}
