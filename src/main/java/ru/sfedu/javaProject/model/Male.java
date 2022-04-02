package ru.sfedu.javaProject.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;



@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Male {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    private String name;
    private final String gender = Gender.MALE.toString();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "male")
    private Set<Pair> ps;
}
