package com.epam.spring.core.service.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.Ticket;
import com.epam.spring.core.domain.User;
import com.epam.spring.core.repository.TicketRepository;
import com.epam.spring.core.service.BookingService;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    private TicketRepository ticketRepository;

    BookingServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public double getTicketsPrice(Event event, LocalDateTime dateTime, User user, Set<Long> seats) {
        return 0;
    }

    @Override
    public void bookTickets(Set<Ticket> tickets) {
        tickets.forEach(ticket -> ticketRepository.add(ticket));
    }

    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
        return ticketRepository.getMap().entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(ticket -> ticket.getEvent().getAirDates().contains(dateTime))
                .collect(Collectors.toSet());
    }
}
