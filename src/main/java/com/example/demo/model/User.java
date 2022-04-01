package ru.sfedu.javaProject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String name;

    private Integer age;

    private String avatar;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

}
