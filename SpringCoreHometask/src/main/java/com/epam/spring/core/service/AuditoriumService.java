package com.epam.spring.core.service;

import com.epam.spring.core.domain.Auditorium;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface AuditoriumService extends AbstractDomainObjectService<Auditorium> {

    @Nullable
    Auditorium getByName(@NonNull String name);
}
