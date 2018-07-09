package com.epam.spring.core.service.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.repository.EventRepository;
import com.epam.spring.core.service.EventService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Component("eventService")
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event getByName(String name) {
        return eventRepository.findSingleOrDefault(name);
    }

    @Override
    public Event save(Event object) {
        return eventRepository.add(object);
    }

    @Override
    public void remove(Long id) {
        eventRepository.remove(id);
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.getById(id);
    }

    @Override
    public double getEventPrice(String name) {
        Event event = getByName(name);
        return event == null ? 0 : event.getBasePrice();
    }

    @Override
    public Collection<Event> getAll() {
        return eventRepository.getMap().entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
