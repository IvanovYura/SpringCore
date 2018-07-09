package com.epam.spring.core.service.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.Ticket;
import com.epam.spring.core.domain.User;
import com.epam.spring.core.service.DiscountService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Component("discountService")
public class DiscountServiceImpl implements DiscountService {

    @Override
    public byte getDiscount(@Nullable User user, Event event, LocalDate airDateTime) {
        double ticketDiscount = calculateTicketDiscount(user, event, airDateTime);
        double birthdayDiscount = calculateBirthdayDiscount(user, event, airDateTime);
        return (byte) (Double.compare(ticketDiscount, birthdayDiscount) > 0 ? ticketDiscount : birthdayDiscount);
    }

    private double calculateTicketDiscount(User user, Event event, LocalDate localDate) {
        List<Ticket> collect = getTicketsForUser(user, event, localDate);
        return collect.size() >= 10 ? (collect.size() / 10) * 0.5 : 0;
    }

    private double calculateBirthdayDiscount(User user, Event event, LocalDate date) {
        List<Ticket> collect = getTicketsForUser(user, event, date);
        if (!collect.isEmpty()) {
            if (user.getBirthday().getDayOfMonth() + 5 > date.getDayOfMonth()
                    && user.getBirthday().getDayOfMonth() - 5 < date.getDayOfMonth()) {
                return 0.05;
            }
        }
        return 0;
    }

    private List<Ticket> getTicketsForUser(User user, Event event, LocalDate date) {
        return user.getTickets().stream().filter(ticket -> ticket.getEvent().equals(event))
                .filter(ticket -> ticket.getDateTime().equals(LocalDateTime.of(date, LocalTime.now())))
                .collect(Collectors.toList());
    }
}
