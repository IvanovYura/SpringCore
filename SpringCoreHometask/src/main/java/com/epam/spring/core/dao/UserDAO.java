package com.epam.spring.core.dao;

import com.epam.spring.core.domain.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface UserDAO {

    @Nullable
    User getUserByEmail(@NonNull  String email);

    @Nullable
    User getUserById(@NonNull Long id);
}
