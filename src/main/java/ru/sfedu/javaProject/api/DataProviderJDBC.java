package ru.sfedu.javaProject.api;

import ru.sfedu.javaProject.Constants;
import ru.sfedu.javaProject.model.Boy;
import ru.sfedu.javaProject.model.Girl;
import ru.sfedu.javaProject.model.Pair;

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
                ConfigurationUtil.getConfigurationEntry(Constants.JDBC_BOY),
                ConfigurationUtil.getConfigurationEntry(Constants.JDBC_PASSWORD));
        return connection;
    }

    private void createTables() throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(Constants.SQL_BOY_TABLE_CREATE);
        connection.close();
    }

    public void dropTables() throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(Constants.SQL_BOY_DROP_TABLE);
        connection.close();
    }
    //BOY
    private List<Boy> getBoyListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Boy> boyList = new ArrayList<>();
        while (resultSet.next()) {
            Boy boy = new Boy();
            boy.setId(resultSet.getLong(1));
            boy.setName(resultSet.getString(2));
            boy.setAge(resultSet.getInt(3));
            boyList.add(boy);
        }
        return boyList;
    }


    @Override
    public void createBoy(Boy boy) throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_BOY_INSERT, boy.getName(), boy.getAge()));
    }

    @Override
    public Optional<Boy> getBoyById(Long id) throws SQLException, IOException {
        Connection connection = getConnection();
        List<Boy> boyList = getBoyListFromResultSet(connection.prepareStatement(String.format(Constants.SQL_BOY_SELECT_BY_ID, id)).executeQuery());
        return boyList.stream().findAny();
    }

    @Override
    public void updateBoy(Boy boy) throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_BOY_UPDATE_BY_ID, boy.getName(), boy.getAge(), boy.getId()));
    }

    @Override
    public void deleteBoyById(Long id) throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_BOY_DELETE_BY_ID, id));
    }

    //GIRL
    private List<Girl> getGirlListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Girl> girlList = new ArrayList<>();
        while (resultSet.next()) {
            Girl girl = new Girl();
            girl.setId(resultSet.getLong(1));
            girl.setName(resultSet.getString(2));
            girl.setAge(resultSet.getInt(3));
            girlList.add(girl);
        }
        return girlList;
    }


    @Override
    public void createGirl(Girl girl) throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_GIRL_INSERT, girl.getName(), girl.getAge()));
    }

    @Override
    public Optional<Girl> getGirlById(Long id) throws SQLException, IOException {
        Connection connection = getConnection();
        List<Girl> girlList = getGirlListFromResultSet(connection.prepareStatement(String.format(Constants.SQL_GIRL_SELECT_BY_ID, id)).executeQuery());
        return girlList.stream().findAny();
    }

    @Override
    public void updateGirl(Girl girl) throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_GIRL_UPDATE_BY_ID, girl.getName(), girl.getAge(), girl.getId()));
    }

    @Override
    public void deleteGirlById(Long id) throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_GIRL_DELETE_BY_ID, id));
    }

    //PAIR
    private List<Pair> getPairListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Pair> pairList = new ArrayList<>();
        while (resultSet.next()) {
            Pair pair = new Pair();
            pair.setId(resultSet.getLong(1));
            pair.setB_id(resultSet.getLong(2));
            pair.setG_id(resultSet.getLong(3));
            pairList.add(pair);
        }
        return pairList;
    }


    @Override
    public void createPair(Pair pair) throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_PAIR_INSERT, pair.getB_id(), pair.getG_id()));
    }

    @Override
    public Optional<Pair> getPairById(Long id) throws SQLException, IOException {
        Connection connection = getConnection();
        List<Pair> pairList = getPairListFromResultSet(connection.prepareStatement(String.format(Constants.SQL_PAIR_SELECT_BY_ID, id)).executeQuery());
        return pairList.stream().findAny();
    }

    @Override
    public void updatePair(Pair pair) throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_PAIR_UPDATE_BY_ID, pair.getB_id(), pair.getG_id(), pair.getId()));
    }

    @Override
    public void deletePairById(Long id) throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_PAIR_DELETE_BY_ID, id));
    }
}