package com.kodilla.kodilla.diplomaBackend.controller;

import com.kodilla.kodilla.diplomaBackend.domain.Rent;
import com.kodilla.kodilla.diplomaBackend.domain.RentDto;
import com.kodilla.kodilla.diplomaBackend.domain.User;
import com.kodilla.kodilla.diplomaBackend.mapper.RentMapper;
import com.kodilla.kodilla.diplomaBackend.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.util.calendar.LocalGregorianCalendar;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/rent")
public class RentController {

    @Autowired
    RentService rentService;

    @Autowired
    RentMapper rentMapper;

    @PostMapping(value="/book", consumes = APPLICATION_JSON_VALUE)
    public void booking(@RequestBody RentDto rentDto){
        rentService.makeReservation(rentMapper.mapToRent(rentDto));

    }

    @PutMapping(value="/cancel", consumes = APPLICATION_JSON_VALUE)
    public void cancellingBook(@RequestBody RentDto rentDto){
        rentService.cancelReservation(rentMapper.mapToRent(rentDto));

    }

    @PutMapping(value="/confirm", consumes = APPLICATION_JSON_VALUE)
    public void rent(@RequestBody RentDto rentDto){
        rentService.confirmRent(rentMapper.mapToRent(rentDto));

    }

    @PutMapping(value="/return", consumes = APPLICATION_JSON_VALUE)
    public void returnCar(@RequestBody RentDto rentDto){
        rentService.confirmReturn(rentMapper.mapToRent(rentDto));

    }

    @GetMapping(value="/history")
    public List<RentDto> getRents(@RequestBody User user){
        return rentMapper.mapToRentDtoList(rentService.fetchUserRents(user));
    }

}
