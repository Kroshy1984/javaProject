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
        this.pairRepository = pairRepository;
    }

    public Optional<SUser> getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public Iterable<User> getAllUsers() { return userRepository.findAll(); }

    public void createUser(SUser user){
        userRepository.save(user);
    }

    public void createPair(Pair pair) throws {
        List<User> users = new ArrayList<>(pair.getUsers());
        if(!pairRepository.findAllByUsersContiansAndUsersContains(users.get(0),users.get(1)).iterator().hasNext())
            pairRepository.save(pair);
    }

    public Optional<Pair> getPairById(Long id){
        return pairRepository.findById(id);
    }

    public Iterable<Pair> getAllPairs(){
        
    }
}
