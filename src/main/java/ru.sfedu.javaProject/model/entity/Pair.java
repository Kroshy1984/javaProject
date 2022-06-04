package ru.sfedu.javaProject.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Pairs")
@Data
@NoArgsConstructor
public class Pair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @JoinColumn(name = "user_ids", insertable = false, updatable = false)
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Pairs_Users",
            joinColumns = { @JoinColumn(name = "pair_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false) },
            uniqueConstraints = {@UniqueConstraint(name = "UniqueUserAndPair", columnNames = { "pair_id", "user_id" })}
    )
    private Set<User> users = new HashSet<>();
}