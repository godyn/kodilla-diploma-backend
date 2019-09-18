package com.kodilla.kodilla.diplomaBackend.GoogleCalendar.client;

import com.kodilla.kodilla.diplomaBackend.GoogleCalendar.domain.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CalendarClient {

    @Value("${GoogleCalendar.api.endpoint}")
    private String googleCalendarEndpoint;

    @Autowired
    private RestTemplate restTemplate;

    /*
    public EventDto createRentEvent () {

        URI url = UriComponentsBuilder.fromHttpUrl(googleCalendarEndpoint + "/calendars/calendarId/events")
                .queryParam("fields", "start, end, summary")
                
                .build()
                .encode()
                .toUri();


       return EventDto createdEvent= restTemplate.postForObject(url, null, EventDto.class);
    }
    */

}
