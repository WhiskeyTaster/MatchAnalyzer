package com.whiskeytaster.matchanalyzer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class EventStorage {
    @JsonProperty("Events")
    private final ArrayList<Event> events = new ArrayList<>();
}
