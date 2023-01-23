package com.whiskeytaster.matchanalyzer.driver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whiskeytaster.matchanalyzer.model.EventStorage;

import java.io.File;

public enum Driver {
    INSTANCE;

    private final ObjectMapper objectMapper;

    Driver() {
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    public EventStorage readEventsFromFile(String path) {
        EventStorage events = new EventStorage();
        try {
            events = objectMapper.readValue(new File(path), EventStorage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }
}
