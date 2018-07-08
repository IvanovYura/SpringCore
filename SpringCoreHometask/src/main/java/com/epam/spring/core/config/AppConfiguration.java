package com.epam.spring.core.config;

import com.epam.spring.core.App;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.epam.spring.core")
@EnableAspectJAutoProxy
public class AppConfiguration {

    @Bean(name = "app")
    public App getApp() {
        return new App();
    }

}
