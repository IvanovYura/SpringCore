package com.epam.spring.core.aspects;

import com.epam.spring.core.repository.CounterRepository;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DiscountAspect {

    @Pointcut("execution(* com.epam.spring.core.service.DiscountService.getDiscount(..))")
    public void discountGettingInvocationAmount() {
    }

    @After("discountGettingInvocationAmount()")
    public void countGetDiscountInvocationAmount() {
        CounterRepository.add("Discount", 1L);
    }

}
