package com.kodilla.kodilla.diplomaBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PENALTIES")
public class Penalty {

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Id
    private long id;

    @Column
    private PenaltyReason reason;

    @Column
    private String details;

    @Column
    private BigDecimal toBePaid;

    @ManyToOne
    @JoinColumn(name="RENT_ID")
    private Rent rent;

}
