package ru.sfedu.javaProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.javaProject.model.SUser;
import ru.sfedu.javaProject.repositories.UserRepository;

import java.util.Optional;

@Service
public class DefaultService {

    private UserRepository userRepository;

    @Autowired
    DefaultService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<SUser> getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public void createUser(SUser user){
        userRepository.save(user);
    }
}
