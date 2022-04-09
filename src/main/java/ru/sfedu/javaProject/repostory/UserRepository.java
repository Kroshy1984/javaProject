package ru.sfedu.javaProject.repostory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sfedu.javaProject.model.User2;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User2, Long> {

    Optional<User2> findUserById(Long id);

}
