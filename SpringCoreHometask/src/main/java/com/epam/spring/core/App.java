package com.epam.spring.core;

import com.epam.spring.core.service.AuditoriumService;
import com.epam.spring.core.service.BookingService;
import com.epam.spring.core.service.EventService;
import com.epam.spring.core.service.UserService;
import lombok.Setter;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    @Setter
    public UserService userService;

    @Setter
    public EventService eventService;

    @Setter
    public AuditoriumService auditoriumService;

    @Setter
    public BookingService bookingService;

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        new ConsoleApp(context.getBean("app")).start();
    }
}
