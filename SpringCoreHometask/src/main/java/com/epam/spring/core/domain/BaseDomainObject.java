package com.epam.spring.core.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public abstract class BaseDomainObject {

    @Setter
    @Getter
    private Long id;
}
