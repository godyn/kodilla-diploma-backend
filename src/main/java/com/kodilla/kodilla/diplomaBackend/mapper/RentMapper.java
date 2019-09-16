package com.kodilla.kodilla.diplomaBackend.mapper;

import com.kodilla.kodilla.diplomaBackend.domain.Rent;
import com.kodilla.kodilla.diplomaBackend.domain.RentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentMapper {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CarMapper carMapper;

    public Rent mapToRent(RentDto rentDto){
        return new Rent(rentDto.getId(),
                userMapper.mapToUser(rentDto.getUser()),
                carMapper.mapToCar(rentDto.getCarRented()),
                rentDto.getStartDay(),
                rentDto.getEndDay(),
                rentDto.getFuelLevel(),
                rentDto.isWithInsurance(),
                rentDto.isWithExtraCarTrunk(),
                rentDto.getListOfPenalties(),
                rentDto.getToBePaid(),
                rentDto.getStatus());
    }

    public RentDto mapToRentDto(Rent rent){
        return new RentDto.RentDtoBuilder()
                .id(rent.getId())
                .user(userMapper.mapToUserDto(rent.getUser()))
                .carRented(carMapper.mapToCarDto(rent.getCarRented()))
                .startDay(rent.getStartDay())
                .endDay(rent.getEndDay())
                .fuelLevel(rent.getFuelLevel())
                .withInsurance(rent.isWithInsurance())
                .withExtraCarTrunk(rent.isWithExtraCarTrunk())
                .penalties(rent.getListOfPenalties())
                .toBePaid(rent.getToBePaid())
                .status(rent.getStatus())
                .build();

    }

    public List<RentDto> mapToRentDtoList(final List<Rent> rentsList){
        return rentsList.stream()
                .map(rent -> mapToRentDto(rent))
                .collect(Collectors.toList());
    }

    public List<Rent> mapToRentList(final List<RentDto> rentsDtoList){
        return rentsDtoList.stream()
                .map(rentDto -> mapToRent(rentDto))
                .collect(Collectors.toList());
    }

}
