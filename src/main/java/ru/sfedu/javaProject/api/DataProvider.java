package ru.sfedu.javaProject.api;

import ru.sfedu.javaProject.model.User;

import java.io.IOException;
import java.sql.SQLException;

public interface DataProvider {

    void createUser(User user) throws SQLException, IOException;

    User getUserById(Long id) throws SQLException, IOException;

    void updateUser(User user) throws SQLException, IOException;

    void deleteUserById(Long id) throws SQLException, IOException;

}
