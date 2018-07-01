package com.epam.spring.core.domain;

import lombok.Getter;
import lombok.Setter;

public abstract class BaseDomainObject {

    @Setter
    @Getter
    private Long id;
}
