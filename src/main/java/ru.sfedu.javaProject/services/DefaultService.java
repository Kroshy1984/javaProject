package ru.sfedu.javaProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.javaProject.model.Pair;
import ru.sfedu.javaProject.model.User;
import ru.sfedu.javaProject.repositories.PairRepository;
import ru.sfedu.javaProject.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultService {

    private UserRepository userRepository;
    private PairRepository pairRepository;

    @Autowired
    DefaultService(UserRepository userRepository, PairRepository pairRepository) {
        this.userRepository = userRepository;
        this.pairRepository = pairRepository;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void createPair(Pair pair) {
        List<User> users = new ArrayList<>(pair.getUsers());
        if (!pairRepository.findAllByUsersContainsAndUsersContains(users.get(0), users.get(1)).iterator().hasNext())
            pairRepository.save(pair);
    }

    public Optional<Pair> getPairById(Long id) {
        return pairRepository.findById(id);
    }

    public Iterable<Pair> getAllPairs() {
        return pairRepository.findAll();
    }

    public Iterable<Pair> getPairByUser(User user) {
        return pairRepository.findAllByUsersContains(user);
    }
}
