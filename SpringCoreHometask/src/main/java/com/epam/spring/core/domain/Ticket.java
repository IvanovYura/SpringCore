package com.epam.spring.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
public class Ticket extends BaseDomainObject implements Comparable<Ticket> {

    @Getter
    private User user;

    @Getter
    private Event event;

    @Getter
    private LocalDateTime dateTime;

    @Getter
    private long seat;

    public Ticket(User user, Event event, LocalDateTime dateTime, long seat) {
        this.user = user;
        this.event = event;
        this.dateTime = dateTime;
        this.seat = seat;
    }

    @Override
    public int compareTo(Ticket other) {
        if (other == null) {
            return 1;
        }
        int result = dateTime.compareTo(other.getDateTime());

        if (result == 0) {
            result = event.getName().compareTo(other.getEvent().getName());
        }
        if (result == 0) {
            result = Long.compare(seat, other.getSeat());
        }
        return result;
    }
}
