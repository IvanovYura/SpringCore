package com.epam.spring.core.repository;

import com.epam.spring.core.domain.Auditorium;
import com.epam.spring.core.domain.Event;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class AuditoriumRepository extends MapBasedRepository<Auditorium> {

    AuditoriumRepository(Collection<Auditorium> auditoriums) {
        super(auditoriums);
    }

    public Auditorium findSingleOrDefault(@NonNull String name) {
        Optional<Auditorium> auditorium = getMap().entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(e -> e.getName().equals(name))
                .findFirst();

        return auditorium.orElse(null);
    }
}
