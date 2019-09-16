package com.kodilla.kodilla.diplomaBackend.mapper;

import com.kodilla.kodilla.diplomaBackend.domain.Car;
import com.kodilla.kodilla.diplomaBackend.domain.CarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    @Autowired
    RentMapper rentMapper;

    public CarDto mapToCarDto(Car car){
        return new CarDto.CarDtoBuilder()
                .id(car.getId())
                .model(car.getModel())
                .productionYear(car.getProductionYear())
                .vehicleMileage(car.getVehicleMileage())
                .doorQuantity(car.getDoorQuantity())
                .seatsQuantity(car.getSeatsQuantity())
                .category(car.getCategory())
                .listOfRents(rentMapper.mapToRentDtoList(car.getListOfRents()))
                .build();
    }

    public Car mapToCar(CarDto carDto){
        return new Car(carDto.getId(), carDto.getModel(), carDto.getProductionYear(),
                carDto.getVehicleMileage(), carDto.getDoorQuantity(), carDto.getSeatsQuantity(),
                carDto.getCategory(), rentMapper.mapToRentList(carDto.getListOfRents()));
    }

    public List<CarDto> mapToCarDtoList(List<Car> cars){
       return cars.stream()
               .map(car -> mapToCarDto(car))
               .collect(Collectors.toList());
    }

    public List<Car> mapToCarList(List<CarDto> carsDto){
        return carsDto.stream()
                .map(carDto -> mapToCar(carDto))
                .collect(Collectors.toList());
    }
}
