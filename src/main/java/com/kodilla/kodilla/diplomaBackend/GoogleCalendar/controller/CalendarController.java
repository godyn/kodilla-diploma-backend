package com.kodilla.kodilla.diplomaBackend.GoogleCalendar.controller;


import com.kodilla.kodilla.diplomaBackend.GoogleCalendar.service.CalendarService;
import com.kodilla.kodilla.diplomaBackend.domain.RentDto;
import com.kodilla.kodilla.diplomaBackend.mapper.RentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/calendar")
public class CalendarController {

    @Autowired
    CalendarService calendarService;

    @Autowired
    RentMapper rentMapper;

    @PostMapping(value = "/createCalendarEvent")
    public void createCalendarEvent(@RequestBody RentDto rentDto){
        calendarService.addEvent(rentMapper.mapToRent(rentDto));
    }
}
