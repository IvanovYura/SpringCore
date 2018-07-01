package com.epam.spring.core;

import com.epam.spring.core.service.UserService;
import lombok.Setter;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    @Setter
    public UserService userService;

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        new ConsoleApp((App) context.getBean("app")).start();
    }
}
