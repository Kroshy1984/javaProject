package ru.sfedu.javaProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sfedu.javaProject.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
