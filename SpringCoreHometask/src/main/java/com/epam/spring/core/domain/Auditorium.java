package com.epam.spring.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@EqualsAndHashCode
@ToString
public class Auditorium extends BaseDomainObject {

    @Setter
    @Getter
    private String name;

    @Getter
    @Setter
    private long numberOfSeats;

    @Setter
    @Getter
    private Set<Long> vipSeats = Collections.emptySet();

    public Auditorium() {
    }

    public long countVipSeats(Collection<Long> seats) {
        return seats.stream().filter(seat -> vipSeats.contains(seat)).count();
    }

    public Set<Long> getAllSeats() {
        return LongStream.rangeClosed(1, numberOfSeats).boxed().collect(Collectors.toSet());
    }
}
