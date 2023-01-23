package com.whiskeytaster.matchanalyzer.config;


import com.whiskeytaster.matchanalyzer.driver.Driver;
import com.whiskeytaster.matchanalyzer.model.EventStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public EventStorage getEventStorage() {
        Driver driver = getDriver();
        return driver.readEventsFromFile("src/main/resources/data/data.json");
    }

    @Bean
    public Driver getDriver() {
        return Driver.INSTANCE;
    }
}
