package com.kodilla.kodilla.diplomaBackend.controller;

import com.kodilla.kodilla.diplomaBackend.domain.dto.CarDto;
import com.kodilla.kodilla.diplomaBackend.mapper.CarMapper;
import com.kodilla.kodilla.diplomaBackend.service.CarService;
import com.kodilla.kodilla.diplomaBackend.service.LogHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    CarMapper carMapper;

    @Autowired
    LogHistoryService logHistoryService;

    @GetMapping(value="/cars")
    public List<CarDto> searchCars(@RequestParam(name = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam(name = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate, @RequestParam(name = "category") String category){
        return carMapper.mapToCarDtoList(carService.searchCar(startDate, endDate, category));
    }

    @PostMapping(value="/car", consumes = APPLICATION_JSON_VALUE)
    public void addNewCar(@RequestBody CarDto carDto){
        carService.addCar(carMapper.mapToCar(carDto));
    }

    @DeleteMapping(value="/car")
    public void deleteCar(@RequestBody CarDto carDto){
        carService.deleteCar(carMapper.mapToCar(carDto));
    }

    @PutMapping(value="/car", consumes = APPLICATION_JSON_VALUE)
    public CarDto updateCarData(@RequestBody CarDto carDto){
        return carMapper.mapToCarDto(carService.updateCar(carMapper.mapToCar(carDto)));
    }


}
