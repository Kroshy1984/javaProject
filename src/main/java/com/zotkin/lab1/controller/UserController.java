package com.zotkin.lab1.controller;

import com.zotkin.lab1.entity.UserEntity;
import com.zotkin.lab1.repository.UserRepo;
import com.zotkin.lab1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody UserEntity user){
        try {
            userService.createUser(user);
            return ResponseEntity.ok("Пользователь добавлен!");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Ошибка при добавлении пользователя");
        }
    }


    @GetMapping
    public ResponseEntity getOneUser(@RequestParam Long id){
        try {
            return ResponseEntity.ok(userService.getOneUser(id));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.delete(id));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
