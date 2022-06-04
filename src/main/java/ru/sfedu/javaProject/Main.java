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
        /*User user = new User();
        user.setFirstname("Глебова");
        user.setSurname("Софья");
        user.setPatronymic("Николаевна");

        try {
            DataProvider dataProvider = DataProviderJDBC.getInstance();
            dataProvider.createUser(user);
            dataProvider.getUserById(1L).ifPresentOrElse(System.out::println, () -> System.out.println("user not found"));
            dataProvider.deleteUserById(1L);
            dataProvider.getUserById(1L).ifPresentOrElse(System.out::println, () -> System.out.println("user not found"));

            DataProviderJDBC.getInstance().dropTables();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }*/

    }

}
