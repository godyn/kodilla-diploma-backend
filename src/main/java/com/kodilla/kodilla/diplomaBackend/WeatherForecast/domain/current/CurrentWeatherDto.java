package com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherDto {

    @JsonProperty("city")
    private Location location;

    @JsonProperty("list")
    private List<Weather> weatherList;

}
