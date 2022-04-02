package ru.sfedu.javaProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sfedu.javaProject.model.Female;

@Repository
public interface FemaleRepository extends JpaRepository<Female, Long> {

}
