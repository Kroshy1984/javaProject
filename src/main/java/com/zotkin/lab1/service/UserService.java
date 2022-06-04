package com.zotkin.lab1.service;

import com.zotkin.lab1.entity.UserEntity;
import com.zotkin.lab1.model.User;
import com.zotkin.lab1.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity createUser(UserEntity user){
        return userRepo.save(user);
    }

    public User getOneUser(Long id){
        UserEntity find = userRepo.findById(id).get();
        return User.toModel(find);
    }

    public Long delete(Long id){
        userRepo.deleteById(id);
        return id;
    }
}
