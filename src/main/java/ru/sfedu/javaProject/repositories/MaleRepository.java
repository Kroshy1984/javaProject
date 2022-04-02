package ru.sfedu.javaProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.javaProject.model.gender_models.Male;

public interface MaleRepository extends JpaRepository<Male, Long> {
}