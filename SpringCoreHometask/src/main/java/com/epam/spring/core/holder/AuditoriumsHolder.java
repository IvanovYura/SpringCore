package com.epam.spring.core.holder;

import com.epam.spring.core.domain.Auditorium;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Component("auditoriumsHolder")
public class AuditoriumsHolder {

    @Value("${auditorium.room1.name}")
    private String roomName;

    @Value("${auditorium.room1.seats}")
    private Long numberOfSeats;

    @Getter
    Collection<Auditorium> auditoriums = new ArrayList<>();

    @PostConstruct
    @SuppressWarnings("unchecked")
    void init() {
        auditoriums.add(Auditorium.create(
                roomName,
                numberOfSeats,
                new HashSet() {{
                    add(4);
                    add(5);
                    add(6);
                }}
        ));
    }
}
