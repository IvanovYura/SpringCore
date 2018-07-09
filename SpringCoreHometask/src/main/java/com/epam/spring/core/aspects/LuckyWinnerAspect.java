package com.epam.spring.core.aspects;

import com.epam.spring.core.domain.Ticket;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Set;

@Aspect
@Component
public class LuckyWinnerAspect {

    @Pointcut("execution(* com.epam.spring.core.service.BookingService.bookTickets(..)) && args(tickets))")
    public void bookTickets(Set<Ticket> tickets) {
    }

    @Before("bookTickets(tickets)")
    public void getLuckyWinner(Set<Ticket> tickets) throws Throwable {
        for (Ticket ticket : tickets) {
            if (ticket.getUser().contains("iurii_ivanov@epam.com")) {
                System.out.println("You are a lucky winner!");
                ticket.getEvent().setBasePrice(0);
                break;
            }
        }
    }
}
