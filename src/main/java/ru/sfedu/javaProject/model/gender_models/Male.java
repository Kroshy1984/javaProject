package ru.sfedu.javaProject.model.gender_models;

import jakarta.persistence.*;
import lombok.Data;
import ru.sfedu.javaProject.model.Gender;
import ru.sfedu.javaProject.model.Pair;
import java.util.Set;

@Entity
@Data
public class Male {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;
    private final String gender = Gender.MALE.toString();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "male")
    private Set<Pair> pairSet;


}
