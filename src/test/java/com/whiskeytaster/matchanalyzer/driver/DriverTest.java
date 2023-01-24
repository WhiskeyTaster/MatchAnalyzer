package com.whiskeytaster.matchanalyzer.driver;

import com.whiskeytaster.matchanalyzer.model.EventStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DriverTest {

    @Test
    public void readEventsFromFile_Expect73() {
        Driver driver = Driver.INSTANCE;
        EventStorage eventService = driver.readEventsFromFile("data");
        assertEquals(73, eventService.getEvents().size());
    }

    @Test
    public void readEventsFromFile_Expect0() {
        Driver driver = Driver.INSTANCE;
        EventStorage eventService = driver.readEventsFromFile("data_invalid2vfg");
        assertEquals(0, eventService.getEvents().size());
    }
}