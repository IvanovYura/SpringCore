package com.epam.spring.core.repository;

import com.epam.spring.core.domain.BaseDomainObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class MapBasedRepository<T extends BaseDomainObject> {

    private Map<Long, T> map = new HashMap<>();
    private AtomicLong id = new AtomicLong();

    MapBasedRepository(Collection<T> collection) {
        collection.forEach(object -> map.putIfAbsent(id.getAndIncrement(), object));
    }

    public T add(T object) {
        map.putIfAbsent(id.getAndIncrement(), object);
        return object;
    }

    public void remove(Long id) {
        map.remove(id);
    }

    public T getById(Long id) {
        return map.get(id);
    }

    public Map<Long, T> getMap() {
        return map;
    }
}
