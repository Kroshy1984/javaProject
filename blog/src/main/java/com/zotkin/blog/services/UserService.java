/*package com.zotkin.blog.services;

import com.zotkin.blog.enums.Role;
import com.zotkin.blog.models.User;
import com.zotkin.blog.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(User user){
        if(userRepo.findByEmail(user.getEmail()) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.USER);
        userRepo.save(user);
        return true;
    }
}
*/