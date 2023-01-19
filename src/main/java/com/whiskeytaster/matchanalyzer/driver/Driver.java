package com.whiskeytaster.matchanalyzer.driver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whiskeytaster.matchanalyzer.service.EventService;

import java.io.File;
public class Driver {
    private final ObjectMapper objectMapper;

    public Driver() {
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    public EventService readEventsFromFile(String path) {
        EventService events = new EventService();
        try {
            events = objectMapper.readValue(new File(path), EventService.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return events;
    }
}
