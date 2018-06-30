package com.epam.spring.core.beans;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Client {

    @Getter
    private String id;
    @Getter
    private String fullName;

    Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
