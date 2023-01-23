package com.whiskeytaster.matchanalyzer.driver;

import com.whiskeytaster.matchanalyzer.model.EventStorage;
import com.whiskeytaster.matchanalyzer.service.EventService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DriverTest {

    @Test
    public void readEventsFromFile_Expect3() {
        Driver driver = Driver.INSTANCE;
        EventStorage eventService = driver.readEventsFromFile("src/test/resources/data/short_data_test.json");
        assertEquals(3, eventService.getEvents().size());
    }

    @Test
    public void readEventsFromFile_Expect0() {
        Driver driver = Driver.INSTANCE;
        EventStorage eventService = driver.readEventsFromFile("src/test/resources/data/short_data_testt.json");
        assertEquals(0, eventService.getEvents().size());
    }
}