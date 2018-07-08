package com.epam.spring.core.repository;

import com.epam.spring.core.domain.Ticket;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class TicketRepository extends MapBasedRepository<Ticket> {

    TicketRepository(Collection<Ticket> collection) {
        super(collection);
    }

    TicketRepository() {

    }
}
