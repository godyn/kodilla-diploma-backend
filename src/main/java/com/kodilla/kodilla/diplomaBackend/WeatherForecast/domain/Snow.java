package com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain.current;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Snow {

    @JsonProperty("3h")
    private float rain;

    public Snow(float rain) {
        this.rain = rain;
    }

    public Snow() {
    }

    public float getRain() {
        return rain;
    }

    public void setRain(float rain) {
        this.rain = rain;
    }
}
