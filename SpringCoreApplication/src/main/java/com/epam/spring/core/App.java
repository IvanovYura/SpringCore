package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.Event;
import com.epam.spring.core.loggers.EventLogger;
import lombok.Getter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class App {

    @Getter
    private EventLogger eventLogger;
    private Client client;

    private Event event;

    private App(Client client, EventLogger logger) {
        this.eventLogger = logger;
        this.client = client;
    }

    public static void main(String[] args) {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring.xml"));
        App app = (App) beanFactory.getBean("app");

    }

    private void logEvent(Event event) throws IOException {
        eventLogger.logEvent(event);
    }


}
