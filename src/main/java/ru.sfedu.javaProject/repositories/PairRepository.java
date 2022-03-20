package ru.sfedu.javaProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sfedu.javaProject.model.Pair;
import ru.sfedu.javaProject.model.User;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PairRepository extends CrudRepository<Pair, Long> {

    Iterable<Pair> findAllByUsersContains(User users);

    Iterable<Pair> findAllByUsersContainsAndUsersContains(User user1, User user2);
}
