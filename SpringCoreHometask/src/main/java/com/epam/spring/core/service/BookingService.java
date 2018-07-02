package com.epam.spring.core.service;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.Ticket;
import com.epam.spring.core.domain.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.Set;

public interface BookingService {

    double getTicketsPrice(@NonNull Event event, @NonNull LocalDateTime dateTime, @Nullable User user,
                           @NonNull Set<Long> seats);

    void bookTickets(@NonNull Set<Ticket> tickets);

    @NonNull
    Set<Ticket> getPurchasedTicketsForEvent(@NonNull Event event, @NonNull LocalDateTime dateTime);
}
