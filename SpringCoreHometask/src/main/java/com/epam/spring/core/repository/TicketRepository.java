package com.epam.spring.core.repository;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.Ticket;

import java.time.LocalDateTime;
import java.util.Collection;

public class TicketRepository extends  MapBasedRepository<Ticket> {

    TicketRepository(Collection<Ticket> tickets) {
        super(tickets);
    }
}
