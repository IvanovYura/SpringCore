package com.epam.spring.core.dao;

import com.epam.spring.core.domain.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Collection;

public interface UserDAO {

    @Nullable
    User getUserByEmail(@NonNull  String email);

    @Nullable
    User getUserById(@NonNull Long id);

    User add(@NonNull User user);

    Collection<User> getAll();

    void remove(Long id);
}
