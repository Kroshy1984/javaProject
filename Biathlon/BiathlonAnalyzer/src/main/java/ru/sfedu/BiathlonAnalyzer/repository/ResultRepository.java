package ru.sfedu.BiathlonAnalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.BiathlonAnalyzer.model.Result;

public interface ResultRepository extends JpaRepository<Result, Long>{
}
