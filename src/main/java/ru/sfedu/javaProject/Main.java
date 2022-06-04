package ru.sfedu.javaProject;

import ru.sfedu.javaProject.api.DataProvider;
import ru.sfedu.javaProject.api.DataProviderJDBC;
import ru.sfedu.javaProject.model.User;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.setFirstname("Фетисов");
        user.setSurname("Сергей");
        user.setPatronymic("Константинович");

        try {
            DataProvider dataProvider = DataProviderJDBC.getInstance();
            dataProvider.createUser(user);
            dataProvider.getUserById(1L).ifPresentOrElse(System.out::println, () -> System.out.println("user not found"));
            dataProvider.deleteUserById(1L);
            dataProvider.getUserById(1L).ifPresentOrElse(System.out::println, () -> System.out.println("user not found"));

            DataProviderJDBC.getInstance().dropTables();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

}