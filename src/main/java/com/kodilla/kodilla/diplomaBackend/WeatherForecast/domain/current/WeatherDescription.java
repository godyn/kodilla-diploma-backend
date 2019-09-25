package com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDescription {

    @JsonProperty("list.weather.main")
    private String group;

    @JsonProperty("list.weather.description")
    private String description;
}
