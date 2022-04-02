package ru.sfedu.javaProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sfedu.javaProject.model.Pair;

@Repository
public interface PairRepository extends JpaRepository<Pair, Long> {

}
