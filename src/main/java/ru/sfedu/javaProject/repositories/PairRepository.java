package ru.sfedu.javaProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.javaProject.model.Pair;

public interface PairRepository extends JpaRepository<Pair, Long> {
}