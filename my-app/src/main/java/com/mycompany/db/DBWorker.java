package com.mycompany.db;

import interfaces.DBRepository;

import com.mycompany.utils.ConfigurationUtil;
import com.mycompany.Constants;
import com.mycompany.models.Boy;
import com.mycompany.models.Girl;

//import java.beans.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Statement;

import static com.mycompany.utils.ConfigurationUtil.getConfigurationEntry;

public class DBWorker implements DBRepository{
    private static DBWorker instance = null;

    private DBWorker() throws SQLException, IOException, ClassNotFoundException {
        Class.forName(getConfigurationEntry(Constants.JDBC_DRIVER));
        createTables();
    }

    public static DBWorker getInstance() throws SQLException, IOException, ClassNotFoundException {
        if (instance == null)
         instance = new DBWorker();
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
        connection.createStatement().executeUpdate(Constants.SQL_CREATE_BOYS_TABLE);
        connection.createStatement().executeUpdate(Constants.SQL_CREATE_GIRLS_TABLE);
        connection.createStatement().executeUpdate(Constants.SQL_CREATE_PAIR_TABLE);
        connection.close();
    }

    public void dropTables() throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(Constants.SQL_DROP_BOYS_TABLE);
        connection.close();
    }

    private List<Boy> getBoyListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Boy> BoyList = new ArrayList<>();
        while (resultSet.next()) {
            Boy Boy = new Boy();
            Boy.setId(resultSet.getLong(1));
            Boy.setName(resultSet.getString(2));
            BoyList.add(Boy);
        }
        return BoyList;
    }


    @Override
    public void createBoy(Boy Boy) throws SQLException, IOException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
         int res = statement.executeUpdate(String.format(Constants.SQL_Boy_INSERT, Boy.getName()),Statement.RETURN_GENERATED_KEYS);
        if(res == 1){
            if(statement.getGeneratedKeys().next())
         Boy.setId(statement.getGeneratedKeys().getLong("id"));
        }
    }

    @Override
    public Optional<Boy> getBoyById(Long id) throws SQLException, IOException {
        Connection connection = getConnection();
        List<Boy> BoyList = getBoyListFromResultSet(connection.prepareStatement(String.format(Constants.SQL_Boy_SELECT_BY_ID, id)).executeQuery());
        return BoyList.stream().findAny();
    }

    @Override
    public void updateBoy(Boy Boy) throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_Boy_UPDATE_BY_ID, Boy.getName(), Boy.getId()));
    }

    @Override
    public void deleteBoyById(Long id) throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_Boy_DELETE_BY_ID, id));
    }

    private List<Girl> getGirlListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Girl> GirlList = new ArrayList<>();
        while (resultSet.next()) {
            Girl Girl = new Girl();
            Girl.setId(resultSet.getLong(1));
            Girl.setName(resultSet.getString(2));
            GirlList.add(Girl);
        }
        return GirlList;
    }


    @Override
    public void createGirl(Girl Girl) throws SQLException, IOException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
         int res = statement.executeUpdate(String.format(Constants.SQL_Boy_INSERT, Girl.getName()),Statement.RETURN_GENERATED_KEYS);
        if(res == 1){
            if(statement.getGeneratedKeys().next())
         Girl.setId(statement.getGeneratedKeys().getLong("id"));
        }
    }

    @Override
    public Optional<Girl> getGirlById(Long id) throws SQLException, IOException {
        Connection connection = getConnection();
        List<Girl> GirlList = getGirlListFromResultSet(connection.prepareStatement(String.format(Constants.SQL_Girl_SELECT_BY_ID, id)).executeQuery());
        return GirlList.stream().findAny();
    }

    @Override
    public void updateGirl(Girl Girl) throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_Girl_UPDATE_BY_ID, Girl.getName(), Girl.getId()));
    }

    @Override
    public void deleteGirlById(Long id) throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_Girl_DELETE_BY_ID, id));
    }
   
    @Override
    public void createPair(Boy boy,Girl girl) throws SQLException, IOException{
        if(!getBoyById(boy.getId()).isPresent()){
            createBoy(boy);
        }

        if(!getGirlById(girl.getId()).isPresent()){
            createGirl(girl);
        }

        Connection connection = getConnection();
        connection.createStatement().executeUpdate(String.format(Constants.SQL_PAIR_INSERT,boy.getId(),girl.getId()));
    }

}
