package com.example.demo.controllers;

import com.example.demo.Constants;
import com.example.demo.model.Gender;
import com.example.demo.model.Pair;
import com.example.demo.model.User;
import com.example.demo.services.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
public class DefaultController {

    private DefaultService defaultService;

    @Autowired
    DefaultController(DefaultService defaultService) {
        this.defaultService = defaultService;
    }

    @GetMapping(path = {"user/get/{id}"})
    @ResponseBody
    public String getUser(@PathVariable Long id) {
        try {
            User user = defaultService.getUserById(id).orElseThrow(() -> new IllegalArgumentException(Constants.ERROR_USER_NOT_FOUND));
            return user.toString();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = {"user/getAll"})
    @ResponseBody
    public String getAllUsers() {
        Iterable<User> users = defaultService.getAllUsers();
        StringBuilder response = new StringBuilder();
        if (!users.iterator().hasNext()) response.append(Constants.ERROR_USER_NOT_FOUND);
        else users.forEach(user -> response.append(user).append("</br>"));
        return response.toString();
    }

    @GetMapping(path = {"user/create/{age}&{gender}&{name}"})
    @ResponseBody
    public String createUser(@PathVariable Integer age, @PathVariable Gender gender, @PathVariable String name) {
        try {
            User user = new User();
            user.setAge(age);
            user.setGender(gender);
            user.setName(name);
            defaultService.createUser(user);
            return "ok";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = {"pair/get/{id}"})
    @ResponseBody
    public String getPair(@PathVariable Long id) {
        try {
            Pair pair = defaultService.getPairById(id).orElseThrow(() -> new IllegalArgumentException(Constants.ERROR_PAIR_NOT_FOUND));
            return pair.toString();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = {"pair/getAll"})
    @ResponseBody
    public String getAllPairs() {
        Iterable<Pair> pairs = defaultService.getAllPairs();
        StringBuilder response = new StringBuilder();
        if (!pairs.iterator().hasNext()) response.append(Constants.ERROR_PAIR_NOT_FOUND);
        else pairs.forEach(pair -> response.append(pair).append("</br>"));
        return response.toString();
    }

    @GetMapping(path = {"pair/getByUserId/{id}"})
    @ResponseBody
    public String getPairByUser(@PathVariable Long id) {
        try {
            Iterable<Pair> pairs = defaultService.getPairByUser(defaultService.getUserById(id).orElseThrow(() -> new IllegalArgumentException("user not found")));
            if (!pairs.iterator().hasNext()) throw new IllegalArgumentException(Constants.ERROR_PAIR_NOT_FOUND);
            StringBuilder response = new StringBuilder();
            pairs.forEach(pair -> response.append(pair).append("</br>"));
            return response.toString();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = {"pair/create/{first_user_id}&{second_user_id}"})
    @ResponseBody
    public String createUser(@PathVariable Long first_user_id, @PathVariable Long second_user_id) {
        try {
            Pair pair = new Pair();
            pair.setUsers(Set.of(
                    defaultService.getUserById(first_user_id).orElseThrow(() -> new IllegalArgumentException(Constants.ERROR_PAIR_NOT_FOUND)),
                    defaultService.getUserById(second_user_id).orElseThrow(() -> new IllegalArgumentException(Constants.ERROR_PAIR_NOT_FOUND))));
            defaultService.createPair(pair);
            return "ok";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

}
