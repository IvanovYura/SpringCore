package com.epam.spring.core.aspects;

import com.epam.spring.core.repository.CounterRepository;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CounterAspect {

    @Pointcut("execution(* com.epam.spring.core.service.EventService.getByName(..))")
    public void eventsInvocationAmount() {
    }

    @Pointcut("execution(* com.epam.spring.core.service.EventService.getEventPrice(..))")
    public void eventPriceInvocationAmount() {
    }

    @Pointcut("execution(* com.epam.spring.core.service.BookingService.bookTickets(..))")
    public void bookTicketInvocationAmount() {
    }

    @After("eventsInvocationAmount()")
    public void countEventInvocationAmount() {
        CounterRepository.add("Event", 1L);
    }

    @After("eventPriceInvocationAmount()")
    public void countEventPriceInvocationAmount() {
        CounterRepository.add("EventPrice", 1L);
    }

    @After("bookTicketInvocationAmount()")
    public void countBookTicketInvocationAmount() {
        CounterRepository.add("BookTicket", 1L);
    }
}
