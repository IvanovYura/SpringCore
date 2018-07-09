package com.epam.spring.core.config;

import com.epam.spring.core.App;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.epam.spring.core")
@PropertySource({"classpath:auditorium.properties"})
public class AppConfiguration {

    @Bean(name = "app")
    public App getApp() {
        return new App();
    }

}
