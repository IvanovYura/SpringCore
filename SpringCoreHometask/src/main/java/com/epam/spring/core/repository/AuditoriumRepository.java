package com.epam.spring.core.repository;

import com.epam.spring.core.domain.Auditorium;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Component
public class AuditoriumRepository extends MapBasedRepository<Auditorium> {

    AuditoriumRepository(@Value("#{auditoriumsHolder.auditoriums}") Collection<Auditorium> auditoriums) {
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
