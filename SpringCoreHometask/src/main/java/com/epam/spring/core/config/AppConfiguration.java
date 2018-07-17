package com.epam.spring.core.config;

import com.epam.spring.core.App;
import org.springframework.context.annotation.*;

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
