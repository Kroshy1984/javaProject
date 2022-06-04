package ru.sfedu.BiathlonAnalyzer.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "race_id")
    private Race race;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sportsman_id")
    private Sportsman sportsman;

    private int place;

    private float courseTotalTime;

    private float shootingTime;

    private int misses;

    @Transient
    private float time;

    @PostLoad
    private void postLoad(){
        this.time = this.shootingTime + this.courseTotalTime;
    }
}
