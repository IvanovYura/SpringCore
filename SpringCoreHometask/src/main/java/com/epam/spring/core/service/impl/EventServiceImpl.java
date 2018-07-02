package com.epam.spring.core.service.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.repository.EventRepository;
import com.epam.spring.core.service.EventService;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

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
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Event getById(Long id) {
        return null;
    }

    @Override
    public Collection<Event> getAll() {
        return eventRepository.getMap().entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
