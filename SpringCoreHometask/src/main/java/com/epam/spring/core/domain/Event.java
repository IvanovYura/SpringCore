package com.epam.spring.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@EqualsAndHashCode
@ToString
public class Event extends BaseDomainObject {

    @Setter
    @Getter
    private String name;

    @Getter
    @Setter
    private Set<LocalDateTime> airDates = new HashSet<>();

    @Setter
    @Getter
    private double basePrice;

    @Setter
    @Getter
    private EventRaiting eventRaiting;

    @Setter
    @Getter
    private Map<LocalDateTime, Auditorium> auditoriums = new HashMap<>();

    public boolean assignAuditorium(LocalDateTime dateTime, Auditorium auditorium) {
        if (airDates.contains(dateTime)) {
            auditoriums.put(dateTime, auditorium);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeAuditoriumAssignment(LocalDateTime dateTime) {
        return auditoriums.remove(dateTime) != null;
    }

    public boolean addAirDateTime(LocalDateTime dateTime) {
        return airDates.add(dateTime);
    }

    public boolean addAirDateTime(LocalDateTime dateTime, Auditorium auditorium) {
        boolean result = airDates.add(dateTime);
        if (result) {
            auditoriums.put(dateTime, auditorium);
        }
        return result;
    }

    public boolean removeAirDateTime(LocalDateTime dateTime) {
        boolean result = airDates.remove(dateTime);
        if (result) {
            auditoriums.remove(dateTime);
        }
        return result;
    }

    public boolean airsOnDateTime(LocalDateTime dateTime) {
        return airDates.stream().anyMatch(dt -> dt.equals(dateTime));
    }

    public boolean airsOnDate(LocalDate date) {
        return airDates.stream().anyMatch(dt -> dt.toLocalDate().equals(date));
    }

    public boolean airsOnDates(LocalDate from, LocalDate to) {
        return airDates.stream()
                .anyMatch(dt -> dt.toLocalDate().compareTo(from) >= 0 && dt.toLocalDate().compareTo(to) <= 0);
    }
}
