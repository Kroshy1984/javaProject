package project.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
public class Pair implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToMany(cascade = { CascadeType.ALL})
    @JoinTable(
            name = "pairs_of_users",
            joinColumns = { @JoinColumn(name = "pair_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") },
            uniqueConstraints = { @UniqueConstraint(name = "UniqueUserAndPair", columnNames = { "pair_id", "user_id" }) }
    )
    private Set<User> users = new HashSet<>();
}


