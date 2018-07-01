package com.epam.spring.core.service;

import com.epam.spring.core.domain.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface UserService extends AbstractDomainObjectService<User> {

    @Nullable
    User getUserByEmail(@NonNull String email);
}
