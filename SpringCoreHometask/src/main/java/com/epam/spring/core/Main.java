package com.epam.spring.core;

import com.epam.spring.core.config.AppConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        App app = context.getBean("app", App.class);
        new ConsoleApp(app).start();
    }
}
