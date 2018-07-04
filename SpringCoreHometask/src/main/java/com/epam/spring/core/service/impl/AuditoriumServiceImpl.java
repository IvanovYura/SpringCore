package com.epam.spring.core.service.impl;

import com.epam.spring.core.domain.Auditorium;
import com.epam.spring.core.repository.AuditoriumRepository;
import com.epam.spring.core.service.AuditoriumService;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class AuditoriumServiceImpl implements AuditoriumService {

    private AuditoriumRepository auditoriumRepository;

    AuditoriumServiceImpl(AuditoriumRepository auditoriumRepository) {
        this.auditoriumRepository = auditoriumRepository;
    }

    @Override
    public Auditorium getByName(String name) {
        return auditoriumRepository.findSingleOrDefault(name);
    }

    @Override
    public Auditorium save(Auditorium auditorium) {
        return auditoriumRepository.add(auditorium);
    }

    @Override
    public void remove(Long id) {
        auditoriumRepository.remove(id);
    }

    @Override
    public Auditorium getById(Long id) {
        return auditoriumRepository.getById(id);
    }

    @Override
    public Collection<Auditorium> getAll() {
        return auditoriumRepository.getMap().entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
