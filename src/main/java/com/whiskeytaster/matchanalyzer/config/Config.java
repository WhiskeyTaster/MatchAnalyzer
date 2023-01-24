package com.whiskeytaster.matchanalyzer.config;


import com.whiskeytaster.matchanalyzer.driver.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Driver getDriver() {
        return Driver.INSTANCE;
    }
}
