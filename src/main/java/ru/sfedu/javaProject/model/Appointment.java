package ru.sfedu.javaProject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity(name = "Appointments")
@Data
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "pair_id", nullable = false)
    private Long pairId;

    @Column(nullable = false)
    private LocalDateTime date;

    /*@JoinColumn(name = "pair_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Pair pair;*/
}
