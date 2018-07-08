package com.epam.spring.core.holder;

import com.epam.spring.core.domain.Auditorium;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Component("auditoriumsHolder")
public class AuditoriumsHolder {

    @Getter
    Collection<Auditorium> auditoriums = new ArrayList<>();

    @SuppressWarnings("unchecked")
    AuditoriumsHolder() {
        auditoriums.add(Auditorium.create(
                "Room 2",
                200,
                new HashSet() {{
                    add(4);
                    add(5);
                    add(6);
                }}
        ));
    }

}
