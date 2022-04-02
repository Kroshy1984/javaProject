package ru.sfedu.javaProject.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Pairs")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="pairs_connect"
                , joinColumns = {@JoinColumn(name = "pair_id")}
                , inverseJoinColumns = {@JoinColumn(name = "user_id")}
            , uniqueConstraints = {@UniqueConstraint(columnNames = {"pair_id", "user_id"})})
    private Set<SUser> users = new HashSet<>();

}
