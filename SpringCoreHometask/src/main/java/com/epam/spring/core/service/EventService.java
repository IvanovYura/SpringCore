package com.epam.spring.core.service;

import com.epam.spring.core.domain.Event;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface EventService extends AbstractDomainObjectService<Event> {

    @Nullable
    Event getByName(@NonNull String name);

    double getEventPrice(@NonNull String name);
}
