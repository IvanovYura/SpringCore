package com.epam.spring.core.service;

import com.epam.spring.core.domain.BaseDomainObject;
import org.springframework.lang.NonNull;

import java.util.Collection;

public interface AbstractDomainObjectService<T extends BaseDomainObject> {

    T save(@NonNull T object);

    void remove(@NonNull Long id);

    T getById(@NonNull Long id);

    @NonNull
    Collection<T> getAll();
}
