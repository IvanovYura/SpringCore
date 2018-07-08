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
    public void countEventsInvocationAmount() {
        //
    }

    @After("countEventsInvocationAmount()")
    public void logEventEnvocationAmount() {
        CounterRepository.add("Event", 1L);
        System.out.println(CounterRepository.get("Event"));
    }

    // get Price -> how many time
    // ticket
    // discount
    // lucky winner -> always true for ME 
}
