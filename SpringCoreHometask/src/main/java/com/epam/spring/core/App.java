package com.epam.spring.core;

import com.epam.spring.core.service.AuditoriumService;
import com.epam.spring.core.service.BookingService;
import com.epam.spring.core.service.DiscountService;
import com.epam.spring.core.service.EventService;
import com.epam.spring.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class App {

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    AuditoriumService auditoriumService;

    @Autowired
    BookingService bookingService;

    @Autowired
    DiscountService discountService;
}
