package ru.sfedu.javaProject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity(name = "Users")
@Data
@NoArgsConstructor
public class User2 {

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
}
