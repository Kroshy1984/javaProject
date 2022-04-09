package ru.sfedu.javaProject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

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
