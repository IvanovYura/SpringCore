package com.epam.spring.core.repository;

import com.epam.spring.core.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Component
public class EventRepository extends MapBasedRepository<Event> {

    @Autowired
    EventRepository(@Value("#{eventsHolder.events}") Collection<Event> events) {
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
