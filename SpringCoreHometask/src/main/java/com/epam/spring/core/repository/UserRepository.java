package com.epam.spring.core.repository;

import com.epam.spring.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Component
public class UserRepository extends MapBasedRepository<User> {

    @Autowired
    UserRepository(@Value("#{usersHolder.users}") Collection<User> users) {
        super(users);
    }

    public User findSingleOrDefault(@NonNull String email) {
        Optional<User> user = getMap().entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(u -> u.getEmail().equals(email))
                .findFirst();

        return user.orElse(null);
    }
}
