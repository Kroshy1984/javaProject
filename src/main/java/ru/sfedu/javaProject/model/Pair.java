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

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Pairs_Users",
            joinColumns = { @JoinColumn(name = "pair_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )

    private Set<User> users = new HashSet<>();
}
