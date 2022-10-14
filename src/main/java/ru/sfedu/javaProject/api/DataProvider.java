package ru.sfedu.javaProject.api;


import ru.sfedu.javaProject.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public interface DataProvider {

    void createUser(User user) throws SQLException, IOException;

    Optional<User> getUserById(Long id) throws SQLException, IOException;

    void updateUser(User user) throws SQLException, IOException;

    void deleteUserById(Long id) throws SQLException, IOException;

}
