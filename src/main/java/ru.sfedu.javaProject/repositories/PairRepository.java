package ru.sfedu.javaProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sfedu.javaProject.model.entity.Pair;
import ru.sfedu.javaProject.model.entity.User;

import java.util.Set;

@Repository
public interface PairRepository extends CrudRepository<Pair, Long> {

    Iterable<Pair> findAllByUsersContains(User users);

    boolean existsByUsersIn(Set<User> users);

}
