package ru.sfedu.javaProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.javaProject.model.gender_models.Female;

public interface FemaleRepository extends JpaRepository<Female, Long> {
}