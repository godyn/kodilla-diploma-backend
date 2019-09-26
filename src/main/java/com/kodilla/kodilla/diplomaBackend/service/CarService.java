package com.kodilla.kodilla.diplomaBackend.service;

import com.kodilla.kodilla.diplomaBackend.domain.Car;
import com.kodilla.kodilla.diplomaBackend.domain.Category;
import com.kodilla.kodilla.diplomaBackend.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    LogHistoryService logHistoryService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    public List<Car> searchCar(LocalDate startDay, LocalDate endDay, String categoryName){
        try{
            Category category = categoryService.getCategory(categoryName);
            return carRepository.searchCarInDateRange(startDay, endDay, category.getId());
        }
        catch(NoSuchElementException e){
            return new ArrayList<>();
        }                                   /////---------> SPRAWDZ
    }

    public Car addCar(Car car){
        logHistoryService.saveLog(userService.getAdmin(), "New car added to the company's fleet: " + car.getModel());
        return carRepository.save(car);
    }

    public void deleteCar(Car car){
        logHistoryService.saveLog(userService.getAdmin(), "Car deleted from the company's fleet: " + car.getModel() + " (" + car.getId() + ")");
        carRepository.delete(car);
    }

    public Car updateCar(Car car){
        logHistoryService.saveLog(userService.getAdmin(), "Car data update: " + car.getModel() + " (" + car.getId() + ")");
        return carRepository.save(car);
    }
}
