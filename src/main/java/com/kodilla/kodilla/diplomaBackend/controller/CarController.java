package com.kodilla.kodilla.diplomaBackend.controller;

import com.kodilla.kodilla.diplomaBackend.domain.CarDto;
import com.kodilla.kodilla.diplomaBackend.mapper.CarMapper;
import com.kodilla.kodilla.diplomaBackend.service.CarService;
import com.kodilla.kodilla.diplomaBackend.service.LogHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping(name="/v1")
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    CarMapper carMapper;

    @Autowired
    LogHistoryService logHistoryService;

    @GetMapping(name="/cars")
    public List<CarDto> searchCars(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam String category){
        return carMapper.mapToCarDtoList(carService.searchCar(startDate, endDate, category));
    }

    @PostMapping(name="/car", consumes = APPLICATION_JSON_VALUE)
    public void addNewCar(@RequestBody CarDto carDto){
        carService.addCar(carMapper.mapToCar(carDto));
    }

    @DeleteMapping(name="/car")
    public void deleteCar(@RequestBody CarDto carDto){
        carService.deleteCar(carMapper.mapToCar(carDto));
    }

    @PutMapping(name="/car", consumes = APPLICATION_JSON_VALUE)
    public CarDto updateCarData(@RequestBody CarDto carDto){
        return carMapper.mapToCarDto(carService.updateCar(carMapper.mapToCar(carDto)));
    }


}
