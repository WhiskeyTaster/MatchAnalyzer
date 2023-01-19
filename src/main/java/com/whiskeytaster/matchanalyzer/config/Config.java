package com.whiskeytaster.matchanalyzer.config;


import com.whiskeytaster.matchanalyzer.driver.Driver;
import com.whiskeytaster.matchanalyzer.service.EventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean("eventService")
    public EventService getEventService() {
        Driver driver = getDriver();
        return driver.readEventsFromFile("src/main/resources/data/data.json");
    }

    @Bean
    public Driver getDriver() {
        return new Driver();
    }
}
