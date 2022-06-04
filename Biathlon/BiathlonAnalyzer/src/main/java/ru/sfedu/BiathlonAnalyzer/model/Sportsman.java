package ru.sfedu.BiathlonAnalyzer.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "sportsmen")
@Entity
public class Sportsman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Counties country;
}
