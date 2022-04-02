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
public class Female {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    private String name;
    private final String gender = Gender.FEMALE.toString();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "female")
    private Set<Pair> ps;
}
