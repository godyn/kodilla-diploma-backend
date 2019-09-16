package com.kodilla.kodilla.diplomaBackend.controller;

import com.kodilla.kodilla.diplomaBackend.domain.Rent;
import com.kodilla.kodilla.diplomaBackend.domain.RentDto;
import com.kodilla.kodilla.diplomaBackend.domain.User;
import com.kodilla.kodilla.diplomaBackend.mapper.RentMapper;
import com.kodilla.kodilla.diplomaBackend.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping(name="/v1/rent")
public class RentController {

    @Autowired
    RentService rentService;

    @Autowired
    RentMapper rentMapper;

    @PostMapping(name="/book", consumes = APPLICATION_JSON_VALUE)
    public void booking(@RequestBody RentDto rentDto){
        rentService.makeReservation(rentMapper.mapToRent(rentDto));

    }

    @PutMapping(name="/cancel", consumes = APPLICATION_JSON_VALUE)
    public void cancellingBook(@RequestBody RentDto rentDto){
        rentService.cancelReservation(rentMapper.mapToRent(rentDto));

    }

    @PutMapping(name="/confirm", consumes = APPLICATION_JSON_VALUE)
    public void rent(@RequestBody RentDto rentDto){
        rentService.confirmRent(rentMapper.mapToRent(rentDto));

    }

    @PutMapping(name="/return", consumes = APPLICATION_JSON_VALUE)
    public void returnCar(@RequestBody RentDto rentDto){
        rentService.confirmReturn(rentMapper.mapToRent(rentDto));

    }

    @GetMapping(name="/history")
    public List<RentDto> getRents(@RequestBody User user){
        return rentMapper.mapToRentDtoList(rentService.fetchUserRents(user));
    }

}
