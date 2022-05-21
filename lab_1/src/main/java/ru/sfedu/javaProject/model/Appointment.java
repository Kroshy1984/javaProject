package ru.sfedu.javaProject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Appointments")
@Data
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "pair_id", nullable = false)
    private Long pairId;

    @Column(nullable = false)
    private LocalDateTime date;

    /*@JoinColumn(name = "pair_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Pair pair;*/
}
