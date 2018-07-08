package com.epam.spring.core.service.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.EventRaiting;
import com.epam.spring.core.domain.Ticket;
import com.epam.spring.core.domain.User;
import com.epam.spring.core.repository.TicketRepository;
import com.epam.spring.core.service.BookingService;
import com.epam.spring.core.service.DiscountService;
import com.sun.istack.internal.NotNull;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component("bookingService")
public class BookingServiceImpl implements BookingService {

    private static final double HIGH_RATE = 1.2;
    private static final double NORMAL_RATE = 1;
    private static final double VIP_RATE = 1;


    private TicketRepository ticketRepository;

    @Setter
    private DiscountService discountService;

    BookingServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public double getTicketsPrice(Event event, @NotNull LocalDate dateTime, User user, Set<Long> seats) {
        if (Objects.isNull(event)) {
            throw new NullPointerException("There is no such event for date time: " + dateTime);
        }
        if (seats.isEmpty() || Objects.isNull(seats)) {
            throw new NullPointerException("There are no seats for such event");
        }
        if (Objects.isNull(user)) {
            throw new NullPointerException("There is no such user");
        }
        double baseSeatPrice = event.getBasePrice();
        double rate = event.getEventRaiting() == EventRaiting.HIGH ? HIGH_RATE : NORMAL_RATE;

        double seatPrice = baseSeatPrice * rate;
        double vipSeatPrice = VIP_RATE * seatPrice;

        double discount = discountService.getDiscount(user, event, dateTime);

        long simpleSeats = event.getAuditoriums().entrySet().stream()
                .mapToLong(entry -> entry.getValue().getNumberOfSeats()).sum();

        long vipSeats = event.getAuditoriums().entrySet().stream()
                .mapToLong(entry -> entry.getValue().getVipSeats().size()).sum();

        double simpleSeatsPrice = simpleSeats * seatPrice;
        double vipSeatsPrice = vipSeats * vipSeatPrice;

        double totalPrice = simpleSeatsPrice + vipSeatsPrice;

        return (1.0 - discount) * totalPrice;
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
