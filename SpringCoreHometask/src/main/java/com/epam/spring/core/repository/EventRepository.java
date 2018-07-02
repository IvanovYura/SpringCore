package com.epam.spring.core.repository;

import com.epam.spring.core.domain.Event;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class EventRepository extends MapBasedRepository<Event> {

    EventRepository(Collection<Event> events) {
        super(events);
    }

    public Event findSingleOrDefault(@NonNull String name) {
        Optional<Event> event = getMap().entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(e -> e.getName().equals(name))
                .findFirst();

        return event.orElse(null);
    }
}
