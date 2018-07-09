package com.epam.spring.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@EqualsAndHashCode
@ToString(exclude = "user")
public class Ticket extends BaseDomainObject implements Comparable<Ticket> {

    private User user;

    public String getUser() {
        return user.getFirstName() + user.getLastName() + user.getEmail();
    }

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
        if (Objects.isNull(other)) {
            return 1;
        }
        Integer result = dateTime.compareTo(other.getDateTime());

        if (result.equals(0)) {
            result = event.getName().compareTo(other.getEvent().getName());
        }
        if (result.equals(0)) {
            result = Long.compare(seat, other.getSeat());
        }
        return result;
    }
}
