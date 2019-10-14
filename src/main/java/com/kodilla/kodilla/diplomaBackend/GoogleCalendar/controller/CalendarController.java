package com.kodilla.kodilla.diplomaBackend.GoogleCalendar.controller;


import com.kodilla.kodilla.diplomaBackend.GoogleCalendar.domain.EventDto;
import com.kodilla.kodilla.diplomaBackend.GoogleCalendar.mapper.EventMapper;
import com.kodilla.kodilla.diplomaBackend.GoogleCalendar.service.CalendarService;
import com.kodilla.kodilla.diplomaBackend.domain.dto.RentDto;
import com.kodilla.kodilla.diplomaBackend.mapper.RentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/calendar")
public class CalendarController {

    @Autowired
    CalendarService calendarService;

    @Autowired
    RentMapper rentMapper;

    @Autowired
    EventMapper eventMapper;

    @PostMapping(value = "/event")
    public String createCalendarEvent(@RequestBody RentDto rentDto){
        return calendarService.addEvent(rentMapper.mapToRent(rentDto));
    }

    @GetMapping(value = "/upcomingEvents")
    public List<EventDto> getUpcomingEvents(){
        return eventMapper.mapToEventDtoList(calendarService.listUpcomingEvents());
    }

    @DeleteMapping(value = "/event")
    public void deleteCalendarEvent(@RequestParam String eventId){
        calendarService.deleteEvent(eventId);
    }

}
