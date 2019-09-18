package com.kodilla.kodilla.diplomaBackend.domain;

import java.math.BigDecimal;
import java.util.List;

public class CategoryDto {

    private long id;
    private String name;
    private String details;
    private BigDecimal pricePerDay;
    private List<CarDto> listOfCarsDto;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public List<CarDto> getListOfCarsDto() {
        return listOfCarsDto;
    }

    public CategoryDto(long id, String name, String details, BigDecimal pricePerDay, List<CarDto> listOfCarsDto) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.pricePerDay = pricePerDay;
        this.listOfCarsDto = listOfCarsDto;
    }

    public CategoryDto() {
    }
}
