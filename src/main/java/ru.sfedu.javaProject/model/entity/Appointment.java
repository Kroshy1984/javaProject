package ru.sfedu.javaProject.model.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Appointments")
@Data
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(name = "UniqueDateAndPair", columnNames = { "date", "pair_id" })})
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "pair_id", nullable = false)
    @JsonAlias("pair_id")
    private Long pairId;

    @Column(nullable = false)
    private LocalDateTime date;

    @JoinColumn(name = "pair_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Pair pair;

}
