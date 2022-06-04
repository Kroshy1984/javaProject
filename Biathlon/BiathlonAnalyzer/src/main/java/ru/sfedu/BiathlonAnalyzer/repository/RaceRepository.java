package ru.sfedu.BiathlonAnalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.BiathlonAnalyzer.model.Race;

public interface RaceRepository extends JpaRepository<Race, Long> {
}
