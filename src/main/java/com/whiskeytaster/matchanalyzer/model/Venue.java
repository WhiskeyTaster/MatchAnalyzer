package com.whiskeytaster.matchanalyzer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Venue {
    private String id;
    private String name;
    private int capacity;
    @JsonProperty("city_name")
    private String cityName;
    @JsonProperty("country_name")
    private String countryName;
    @JsonProperty("map_coordinates")
    private String mapCoordinates;
    @JsonProperty("country_code")
    private String countryCode;
}
