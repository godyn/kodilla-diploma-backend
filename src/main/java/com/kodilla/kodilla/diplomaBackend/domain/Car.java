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
@NamedNativeQuery(
        name="Car.searchCarInDateRange",
        query = "SELECT * FROM CARS C WHERE " +
                "ID NOT IN " +
                    "(SELECET R.CAR_ID FROM RENTS R WHERE " +
                        "(R.START_DAY BETWEEN :REQUESTED_START AND :REQUESTED_END) OR " +
                        "(R.END_DAY BETWEEN :REQUESTED_START AND :REQUESTED_END) OR " +
                        "(R.START_DAY < :REQUESTED_START AND R.END_DAY > :REQUESTED_END ))" +
                "AND C.CATEGORY_ID = :REQUESTED_CATEGORY",
        resultClass = Car.class
)
@Entity
@Table(name="CARS")
public class Car {

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Id
    private long id;

    @Column(name="MODEL")
    private String model;

    @Column(name="PRODUCTION_YEAR")
    private String productionYear;

    @Column(name="VEHICLE_MILEAGE")
    private BigDecimal vehicleMileage;

    @Column(name="DOOR_QTY")
    private int doorQuantity;

    @Column(name="SEATS_QTY")
    private int seatsQuantity;

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "carRented",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    List<Rent> listOfRents=new ArrayList<>();

}
