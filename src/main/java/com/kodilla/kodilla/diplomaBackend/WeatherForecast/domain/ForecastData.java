package com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain.forecast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain.current.Clouds;
import com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain.current.Rain;
import com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain.current.Snow;
import com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain.current.WeatherDescription;
import com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain.current.WeatherMetrics;
import com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain.current.Wind;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastData {

    @JsonProperty("main")
    private WeatherMetrics weatherMetrics;

    @JsonProperty("weather")
    private WeatherDescription weatherDescription;

    @JsonProperty("clouds")
    private com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain.current.Clouds clouds;

    @JsonProperty("wind")
    private com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain.current.Wind windSpeed;

    @JsonProperty("rain")
    private Rain rainLevel;

    @JsonProperty("snow")
    private Snow snow;

    @JsonProperty("dt_txt")
    private String dateTime;

    public ForecastData() {
    }

    public ForecastData(WeatherMetrics weatherMetrics, WeatherDescription weatherDescription, Clouds clouds, Wind windSpeed, Rain rainLevel, Snow snow, String dateTime) {
        this.weatherMetrics = weatherMetrics;
        this.weatherDescription = weatherDescription;
        this.clouds = clouds;
        this.windSpeed = windSpeed;
        this.rainLevel = rainLevel;
        this.snow = snow;
        this.dateTime = dateTime;
    }

    public WeatherMetrics getWeatherMetrics() {
        return weatherMetrics;
    }

    public void setWeatherMetrics(WeatherMetrics weatherMetrics) {
        this.weatherMetrics = weatherMetrics;
    }

    public WeatherDescription getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(WeatherDescription weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Wind windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Rain getRainLevel() {
        return rainLevel;
    }

    public void setRainLevel(Rain rainLevel) {
        this.rainLevel = rainLevel;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
