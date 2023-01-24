package com.whiskeytaster.matchanalyzer.driver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whiskeytaster.matchanalyzer.exception.LoadingInputDataException;
import com.whiskeytaster.matchanalyzer.model.EventStorage;

import java.io.File;

public enum Driver {
    INSTANCE;

    private final ObjectMapper objectMapper;

    Driver() {
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    public EventStorage readEventsFromFile(String fileName) {
        EventStorage events = new EventStorage();
        try {
            String filePath = "src/main/resources/data/";
            events = objectMapper.readValue(new File(filePath + fileName + ".json"), EventStorage.class);
        } catch (Exception e) {
            throw new LoadingInputDataException("File not found or wrong data input");
        }
        return events;
    }
}
