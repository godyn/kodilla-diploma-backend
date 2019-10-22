package com.kodilla.kodilla.diplomaBackend.controller;

import com.google.gson.Gson;
import com.kodilla.kodilla.diplomaBackend.domain.Car;
import com.kodilla.kodilla.diplomaBackend.domain.dto.CarDto;
import com.kodilla.kodilla.diplomaBackend.mapper.CarMapper;
import com.kodilla.kodilla.diplomaBackend.service.CarService;
import com.kodilla.kodilla.diplomaBackend.service.LogHistoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvcHttpRequestsMaker;
    @MockBean
    private CarService carService;
    @MockBean
    private CarMapper carMapper;
    @MockBean
    private LogHistoryService logHistoryService;

    @Test
    public void shouldSearchEmptyList() throws Exception{
        //GIVEN
        List<CarDto> carDtos = new ArrayList<>();
        List<Car> cars = new ArrayList<>();
        LocalDate start = LocalDate.of(2019, 9, 25);
        LocalDate end = LocalDate.of(2019, 9, 28);
        when(carService.searchCar(start, end, "family")).thenReturn(cars);
        when(carMapper.mapToCarDtoList(cars)).thenReturn(carDtos);
        //WHEN & THEN
        mockMvcHttpRequestsMaker.perform(get("/v1/cars")
                                        .param("start", "2019-09-25")
                                        .param("end", "2019-09-28")
                                        .param("category", "family")
                                        .with(user("user").password("userPass").roles("USER"))
                                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldSearchCarList() throws Exception{
        //GIVEN
        List<CarDto> carDtos = new ArrayList<>();
        carDtos.add(new CarDto.CarDtoBuilder().id(1L).model("test_model").build());
        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        LocalDate start = LocalDate.of(2019, 9, 25);
        LocalDate end = LocalDate.of(2019, 9, 28);
        when(carService.searchCar(start, end, "family")).thenReturn(cars);
        when(carMapper.mapToCarDtoList(cars)).thenReturn(carDtos);
        //WHEN & THEN
        mockMvcHttpRequestsMaker.perform(get("/v1/cars")
                .param("start", "2019-09-25")
                .param("end", "2019-09-28")
                .param("category", "family")
                .with(user("user").password("userPass").roles("USER"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].model", is("test_model")));
    }

    @Test
    public void shouldAddNewCar() throws Exception{
        //GIVEN
        CarDto carDto = new CarDto.CarDtoBuilder().id(1L).model("test_model").build();
        Car car = new Car();
        when(carMapper.mapToCar(carDto)).thenReturn(car);
        when(carService.addCar(org.mockito.Matchers.any(Car.class))).thenReturn(car);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);
        //WHEN & THEN
        mockMvcHttpRequestsMaker.perform(post("/v1/car")
                                        .with(user("admin").password("adminPass").roles("ADMIN"))
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .characterEncoding("UTF-8")
                                        .content(jsonContent))
                .andExpect(status().is(200));

    }

    @Test
    public void shouldUpdateCar() throws Exception{
        //GIVEN
        CarDto carDto = new CarDto.CarDtoBuilder().id(1L).model("test_update").build();
        Car car = new Car();
        when(carMapper.mapToCar(carDto)).thenReturn(car);
        when(carService.updateCar(org.mockito.Matchers.any(Car.class))).thenReturn(car);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);
        //WHEN & THEN
        mockMvcHttpRequestsMaker.perform(put("/v1/car")
                .with(user("admin").password("adminPass").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));

    }

    @Test
    public void shouldDeleteCar() throws Exception{
        //GIVEN
        CarDto carDto = new CarDto.CarDtoBuilder().id(1L).model("test_delete").build();
        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);
        //WHEN & THEN
        mockMvcHttpRequestsMaker.perform(delete("/v1/car")
                .with(user("admin").password("adminPass").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }

}