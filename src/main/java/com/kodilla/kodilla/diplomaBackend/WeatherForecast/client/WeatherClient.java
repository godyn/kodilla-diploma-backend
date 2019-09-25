package com.kodilla.kodilla.diplomaBackend.WeatherForecast.client;

import com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain.current.CurrentWeatherDto;
import com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain.forecast.WeatherForecastDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class WeatherForecastClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openweathermap.api.endpoint.prod}")
    private String weatherAppEndpoint;

    @Value("${openweathermap.app.key}")
    private String weatherAppKey;

    public CurrentWeatherDto currentWeather(String cityName, String countryCode){

        URI url = UriComponentsBuilder.fromHttpUrl(weatherAppEndpoint + "/data/2.5/weather")
                .queryParam("q", cityName, countryCode)
                .queryParam("units", "metric")
                .queryParam("lang", "pl")
                .queryParam("APPID", weatherAppKey)
                .build()
                .encode()
                .toUri();
        return restTemplate.getForObject(url, CurrentWeatherDto.class);
    }

    public WeatherForecastDto weatherForecast(String cityName, String countryCode){

        URI url = UriComponentsBuilder.fromHttpUrl(weatherAppEndpoint + "/data/2.5/forecast")
                .queryParam("q", cityName, countryCode)
                .queryParam("units", "metric")
                .queryParam("lang", "pl")
                .queryParam("APPID", weatherAppKey)
                .build()
                .encode()
                .toUri();
        return restTemplate.getForObject(url, WeatherForecastDto.class);
    }


    private URI getUrl(String cityName, String countryCode, String type){
        String endpointType = "";
        switch(type){
            case "current": endpointType = "/data/2.5/weather";
            case "forecast": endpointType="/data/2.5/forecast";
            
        }


        if(type.equals("current")) {
            endpointType = "/data/2.5/weather";
        }
        if(type.equals("forecast")){
            endpointType="/data/2.5/forecast";
        }

        URI url = UriComponentsBuilder.fromHttpUrl(weatherAppEndpoint + endpointType)
                .queryParam("q", cityName, countryCode)
                .queryParam("units", "metric")
                .queryParam("lang", "pl")
                .queryParam("APPID", weatherAppKey)
                .build()
                .encode()
                .toUri();
    }


}
