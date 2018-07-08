package com.epam.spring.core.holder;

import com.epam.spring.core.domain.Auditorium;
import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.EventRaiting;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

@Component("eventsHolder")
public class EventsHolder {

    @Getter
    private Collection<Event> events = new ArrayList<>();

    @SuppressWarnings("unchecked")
    EventsHolder() {
        events.add(Event.create(
                "Avengers",
                new HashSet() {{
                    add(LocalDateTime.parse("2018-03-08T12:30"));
                }},
                250,
                EventRaiting.HIGH,
                new HashMap() {{
                    put(
                            LocalDateTime.parse("2018-03-08T12:30"),
                            Auditorium.create(
                                    "Room 1",
                                    100,
                                    new HashSet() {{
                                        add(1);
                                        add(2);
                                        add(3);
                                    }}
                            )
                    );
                }}
        ));
    }

}
