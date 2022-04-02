package ru.sfedu.javaProject.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String name;

    private String age;

    private String avatar;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    Set<Pair> pairs = new HashSet<>();
}
