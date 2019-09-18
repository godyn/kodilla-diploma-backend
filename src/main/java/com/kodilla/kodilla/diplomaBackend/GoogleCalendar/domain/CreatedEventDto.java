package com.kodilla.kodilla.diplomaBackend.GoogleCalendar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedEventDto {

    @JsonProperty("start")
    LocalDate startDate;

    @JsonProperty("end")
    LocalDate endDate;

    @JsonProperty("summary")
    String title;



}
