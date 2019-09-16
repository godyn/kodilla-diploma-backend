package com.kodilla.kodilla.diplomaBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="LOGS_HISTORY")
public class LogHistory {

    @Column(name="ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @Column(name="TIME_STAMP")
    private Timestamp timeStamp;

    @Column(name="LOG_MESSAGE")
    private String logMessage;
}
