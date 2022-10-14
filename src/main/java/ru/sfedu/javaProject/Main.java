package ru.sfedu.javaProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.sfedu.javaProject.api.DataProvider;
import ru.sfedu.javaProject.api.DataProviderJDBC;
import ru.sfedu.javaProject.model.User;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}