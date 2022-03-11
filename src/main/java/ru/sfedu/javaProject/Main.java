package ru.sfedu.javaProject;

import ru.sfedu.javaProject.api.DataProvider;
import ru.sfedu.javaProject.api.DataProviderJDBC;
import ru.sfedu.javaProject.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.setFirstname("Денис");
        user.setSurname("Госьков");
        user.setPatronymic("Андреевич");

        try {
            DataProvider dataProvider = DataProviderJDBC.getInstance();
            dataProvider.createUser(user);
            System.out.println(dataProvider.getUserById(1L));
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

}
