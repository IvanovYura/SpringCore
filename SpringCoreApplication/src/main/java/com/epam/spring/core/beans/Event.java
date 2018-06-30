package com.epam.spring.core.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.DateFormat;
import java.util.Date;

@ToString
public class Event {

    @Getter
    private int id;

    @Getter
    @Setter
    private String msg;

    @Getter
    private Date date;

    private DateFormat dateFormat;

    public Event(Date date, DateFormat dateFormat) {
        this.date = date;
        this.dateFormat = dateFormat;
    }
}
