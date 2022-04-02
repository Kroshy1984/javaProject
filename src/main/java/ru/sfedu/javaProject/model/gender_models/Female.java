package ru.sfedu.javaProject.model.gender_models;

import jakarta.persistence.*;
import lombok.*;
import ru.sfedu.javaProject.model.Gender;
import ru.sfedu.javaProject.model.Pair;

import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Female {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "female")
    private Set<Pair> pairSet;
}
