package ru.sfedu.javaProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sfedu.javaProject.model.Male;

@Repository
public interface MaleRepository extends JpaRepository<Male, Long> {

}
