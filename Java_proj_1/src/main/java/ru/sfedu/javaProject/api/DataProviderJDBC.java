package ru.sfedu.javaProject.api;

import ru.sfedu.javaProject.Constants;
import ru.sfedu.javaProject.model.User;
import ru.sfedu.javaProject.utils.ConfigurationUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.sfedu.javaProject.utils.ConfigurationUtil.getConfigurationEntry;

public class DataProviderJDBC implements DataProvider {

    private static DataProviderJDBC instance = null;

    private DataProviderJDBC() throws SQLException, IOException, ClassNotFoundException {
        Class.forName(getConfigurationEntry(Constants.JDBC_DRIVER));
        createTables();
    }

    public static DataProviderJDBC getInstance() throws SQLException, IOException, ClassNotFoundException {
        if (instance == null) instance = new DataProviderJDBC();
        return instance;
    }

    private Connection getConnection() throws IOException, SQLException {
        Connection connection = DriverManager.getConnection(
                ConfigurationUtil.getConfigurationEntry(Constants.JDBC_URL),
                ConfigurationUtil.getConfigurationEntry(Constants.JDBC_USER),
                ConfigurationUtil.getConfigurationEntry(Constants.JDBC_PASSWORD));
        return connection;
    }

    private void createTables() throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(Constants.SQL_USER_TABLE_CREATE);
        connection.close();
    }

    public void dropTables() throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(Constants.SQL_USER_DROP_TABLE);
        connection.close();
    }

    private List<User> getUserListFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong(1));
            user.setFirstname(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setPatronymic(resultSet.getString(4));
            userList.add(user);
        }
        return userList;
    }


    @Override
    public void createUser(User user) throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_USER_INSERT, user.getFirstname(), user.getSurname(), user.getPatronymic()));
    }

    @Override
    public Optional<User> getUserById(Long id) throws SQLException, IOException {
        Connection connection = getConnection();
        List<User> userList = getUserListFromResultSet(connection.prepareStatement(String.format(Constants.SQL_USER_SELECT_BY_ID, id)).executeQuery());
        return userList.stream().findAny();
    }

    @Override
    public void updateUser(User user) throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_USER_UPDATE_BY_ID, user.getFirstname(), user.getSurname(), user.getPatronymic(), user.getId()));
    }

    @Override
    public void deleteUserById(Long id) throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_USER_DELETE_BY_ID, id));
    }
}
