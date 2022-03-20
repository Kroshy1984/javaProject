package ru.sfedu.javaProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sfedu.javaProject.model.SUser;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<SUser, Long> {

    Optional<SUser> findUserById(Long id);

}
