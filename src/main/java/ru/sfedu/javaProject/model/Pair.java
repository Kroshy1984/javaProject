package ru.sfedu.javaProject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Pairs")
@Data
@NoArgsConstructor
public class Pair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "first_user_id", nullable = false)
    private Long firstUserId;

    @Column(name = "second_user_id", nullable = false)
    private Long secondUserId;
}
