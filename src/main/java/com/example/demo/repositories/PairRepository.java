package com.example.demo.repositories;

import com.example.demo.model.Pair;
import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.Set;

@Repository
public interface PairRepository extends CrudRepository<Pair, Long> {

    Iterable<Pair> findAllByUsersContains(User users);

    Iterable<Pair> findAllByUsersContainsAndUsersContains(User user1, User user2);
}
