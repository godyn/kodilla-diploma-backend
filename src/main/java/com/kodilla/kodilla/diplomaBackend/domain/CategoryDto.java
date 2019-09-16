package com.kodilla.kodilla.diplomaBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private long id;
    private String name;
    private String details;
    private BigDecimal pricePerDay;
    private List<CarDto> listOfCarsDto;
}
