package com.kodilla.kodilla.diplomaBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/*
@NamedNativeQueries({
        @NamedNativeQuery(name= "Rent.updateRentStatus",
                query="UPDATE RENTS R SET R.STATUS= :NEW_STATUS WHERE R.ID = :THIS_ID",
                resultClass = Rent.class),
        @NamedNativeQuery(name="Rent.updateToBePaid",
                query="UPDATE RENTS R SET R.TO_BE_PAID = :PRICE WHERE R.ID = :THIS_ID",
                resultClass = Rent.class)

})
*/
@Entity
@Table(name="RENTS")
public class Rent {

    @Column(name="ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name="CAR_ID")
    private Car carRented;

    @Column(name="START_DAY")
    private LocalDate startDay;

    @Column(name="END_DAY")
    private LocalDate endDay;

    @Column(name="FUEL_LEVEL")
    private long fuelLevel;

    @Column(name="WITH_INSURANCE")
    private boolean	withInsurance;

    @Column(name="WITH_EXTRA_TRUNK")
    private boolean	withExtraCarTrunk;

    @OneToMany(
            targetEntity = Penalty.class,
            mappedBy = "rent",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private List<Penalty> listOfPenalties;

    @Column(name="TO_BE_PAID")
    private BigDecimal toBePaid;

    @Column(name="STATUS")
    private RentStatus status;




}
