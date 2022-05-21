package ru.sfedu.javaProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sfedu.javaProject.model.Gender;
import ru.sfedu.javaProject.model.SUser;
import ru.sfedu.javaProject.services.DefaultService;

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
        Optional<SUser> user = defaultService.getUserById(id);
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
        SUser user = new SUser();
        user.setAge("20");
        user.setGender(Gender.FEMALE);
        user.setName("Лена");
        defaultService.createUser(user);
        return "ok";
    }
}
