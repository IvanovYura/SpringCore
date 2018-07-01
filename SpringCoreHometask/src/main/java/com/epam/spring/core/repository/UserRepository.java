package com.epam.spring.core.repository;

import com.epam.spring.core.domain.User;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class UserRepository extends MapBasedRepository<User> {

    UserRepository(Collection<User> users) {
        super(users);
    }

    public User findSingleOrDefault(@NonNull String email) {
        Optional<User> user = getMap().entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(u -> u.getEmail().equals(email))
                .findFirst();

        return user.orElse(null);
    }

    public Map<Long, User> getMap() {
        return super.getMap();
    }
}
