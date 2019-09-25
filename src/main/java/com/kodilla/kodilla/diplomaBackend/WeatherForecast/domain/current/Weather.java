package com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    @JsonProperty("main")
    private WeatherMetrics metrics;

    @JsonProperty("weather")
    private WeatherDescription description;

    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("wind")
    private Wind windSpeed;


}
