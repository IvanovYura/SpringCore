package com.epam.spring.core.holder;

import com.epam.spring.core.domain.User;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Component("usersHolder")
public class UsersHolder {

    @Getter
    private Collection<User> users = new ArrayList<>();

    @PostConstruct
    private void init() {
        users.add(User.create(
                "Iurii",
                "Ivanov",
                "iurii_ivanov@epam.com",
                LocalDate.parse("1990-11-26")
        ));
    }
}
