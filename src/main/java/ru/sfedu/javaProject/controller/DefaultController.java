package ru.sfedu.javaProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sfedu.javaProject.model.Gender;
import ru.sfedu.javaProject.model.User2;
import ru.sfedu.javaProject.service.DefaultService;

import java.util.Optional;

@Controller
public class DefaultController {

    private DefaultService defaultService;

    @Autowired
    DefaultController(DefaultService defaultService) {
        this.defaultService = defaultService;
    }

    @GetMapping(path = {"/{id}"})
    @ResponseBody
    public String greeting(@PathVariable Long id) {
        Optional<User2> user = defaultService.getUserById(id);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello ");
        if (user.isPresent()) {
            System.out.println(user.get());
            stringBuilder.append(user.get().getName());
            stringBuilder.append(" !");
        }
        return stringBuilder.toString();
    }

    @GetMapping(path = {"/create"})
    @ResponseBody
    public String createUser() {
        User2 user = new User2();
        user.setAge("20");
        user.setGender(Gender.FEMALE);
        user.setName("Лена");
        defaultService.createUser(user);
        return "ok";
    }
}
