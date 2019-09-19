package com.kodilla.kodilla.diplomaBackend.GoogleCalendar.service;

import com.kodilla.kodilla.diplomaBackend.GoogleCalendar.client.CalendarClient;
import com.kodilla.kodilla.diplomaBackend.domain.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

    @Autowired
    CalendarClient calendarClient;

    public void addEvent(Rent rent){
        calendarClient.createRentEvent(rent);
    }
}
