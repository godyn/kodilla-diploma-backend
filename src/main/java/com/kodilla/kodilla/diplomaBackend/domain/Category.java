package com.kodilla.kodilla.diplomaBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CATEGORIES")
public class Category {

    @Column(name="ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Id
    private long id;

    @Column(name="NAME")
    private String name;

    @Column(name="DETAILS")
    private String details;

    @Column(name="PRICE_PER_DAY")
    private BigDecimal pricePerDay;

    @OneToMany(
            targetEntity = Car.class,
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER,
            mappedBy = "category")
    private List<Car> listOfCars = new ArrayList<>();



}
