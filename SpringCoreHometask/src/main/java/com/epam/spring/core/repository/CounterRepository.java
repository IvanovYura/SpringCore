package com.epam.spring.core.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CounterRepository {

    private static Map<String, Long> map = new HashMap<>();

    public static void add(String key, Long count) {
        if (Objects.isNull(map.computeIfPresent(key, (k, v) -> v + count))) {
            map.put(key, count);
        }
    }

    public static Long get(String name) {
        return map.getOrDefault(name, 0L);
    }

}
