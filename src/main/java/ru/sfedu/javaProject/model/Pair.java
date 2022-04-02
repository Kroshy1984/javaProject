package ru.sfedu.javaProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Pairs")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "male_id", nullable = false)
    private Male male;

    @ManyToOne(optional = false)
    @JoinColumn(name = "female_id", nullable = false)
    private Female female;
}
