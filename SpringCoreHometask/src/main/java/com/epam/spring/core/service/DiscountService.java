package com.epam.spring.core.service;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface DiscountService {

    byte getDiscount(@Nullable User user, @NonNull Event event, @NonNull NonNull airDateTime, long numberOfTickets);
}
