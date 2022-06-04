package ru.sfedu.BiathlonAnalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.BiathlonAnalyzer.model.Sportsman;

public interface SportsmanRepository extends JpaRepository<Sportsman, Long> {
}
