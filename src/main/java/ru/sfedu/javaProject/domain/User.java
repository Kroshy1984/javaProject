package ru.sfedu.javaProject.domain;

import ru.sfedu.javaProject.domain.authentication.Email;
import ru.sfedu.javaProject.domain.authentication.EncodingAlgorithm;
import ru.sfedu.javaProject.domain.authentication.Password;

public class User {
    private String name;
    private Gender gender;
    private Email email;
    private Password password;

    public User(String name, Gender gender, Email email, Password password) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public static User create(String name, Gender gender, String emailAddress, String rawPassword) {
        Email email = Email.create(emailAddress);
        Password password = Password.create(EncodingAlgorithm.BCRYPT, rawPassword);
        return new User(name, gender, email, password);
    }
}
