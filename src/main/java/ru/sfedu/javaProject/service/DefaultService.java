package ru.sfedu.javaProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.javaProject.model.User2;
import ru.sfedu.javaProject.repostory.UserRepository;

import java.util.Optional;

@Service
public class DefaultService {
    private UserRepository userRepository;

    @Autowired
    DefaultService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User2> getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public void createUser(User2 user){
        userRepository.save(user);
    }
}
