package ru.sfedu.BiathlonAnalyzer.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "races")
@Data
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Stages stage;

    @Enumerated(EnumType.STRING)
    private RaceType raceType;
}
