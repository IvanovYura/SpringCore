package com.epam.spring.core;

import com.epam.spring.core.service.AuditoriumService;
import com.epam.spring.core.service.BookingService;
import com.epam.spring.core.service.EventService;
import com.epam.spring.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class App {

    @Autowired
    @Qualifier("userService")
    UserService userService;

    @Autowired
    @Qualifier("eventService")
    EventService eventService;

    @Autowired
    @Qualifier("auditoriumService")
    AuditoriumService auditoriumService;

    @Autowired
    @Qualifier("bookingService")
    BookingService bookingService;
}
