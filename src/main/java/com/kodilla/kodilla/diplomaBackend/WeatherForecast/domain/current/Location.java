package com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    @JsonProperty("id")
    private long cityId;

    @JsonProperty("name")
    private String cityName;

    @JsonProperty("country")
    private String countryCode;
}
