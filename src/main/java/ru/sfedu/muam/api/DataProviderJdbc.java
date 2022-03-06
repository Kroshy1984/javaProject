package ru.sfedu.muam.api;

import ru.sfedu.muam.Constants;
import ru.sfedu.muam.model.Student;
import ru.sfedu.muam.utils.ConfigurationUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataProviderJdbc implements DataProvider{
    public DataProviderJdbc() {
        createTables();
    }

    public Connection getMyConnection() throws IOException, SQLException {
        return DriverManager.getConnection(ConfigurationUtil.getConfigurationEntry(Constants.JDBC_URL).concat(ConfigurationUtil.getConfigurationEntry(Constants.JDBC_PATH)).concat(Constants.JDBC_NAME),
            ConfigurationUtil.getConfigurationEntry(Constants.JDBC_USER),
            ConfigurationUtil.getConfigurationEntry(Constants.JDBC_PASSWORD));
    }

    public void createTables(){
        try{
            Connection connection = getMyConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(Constants.CREATE_TABLE_STUDENTS);
            statement.close();
            connection.close();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteTables(){
        try{
            Connection connection = getMyConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(Constants.DROP_STUDENTS_TABLE);
            statement.close();
            connection.close();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<List<Student>> getAllStudent() {
        try{
            Connection connection = getMyConnection();
            Statement statement = connection.createStatement();
            List<Student> studentList = new ArrayList<>();
            String query = Constants.SELECT_ALL_FROM_STUDENTS;
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                studentList.add(student);
            }

            if (studentList.isEmpty()){
                statement.close();
                connection.close();
                return Optional.empty();
            }

            statement.close();
            connection.close();
            return Optional.of(studentList);
        } catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public boolean addStudentRecord(Student student) {
        try{
            String query = String.format(Constants.INSERT_INTO_STUDENTS, student.getId(), student.getName());
            Connection connection = getMyConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteStudentRecord(String id) {
        try{
            String query = String.format(Constants.DELETE_FROM_STUDENTS, id);
            Connection connection = getMyConnection();
            Statement statement = connection.createStatement();
            if (statement.executeUpdate(query) == 0) {
                statement.close();
                connection.close();
                return false;
            }
            statement.close();
            connection.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateStudentRecord(Student student, String id) {
        try {
            if (deleteStudentRecord(id)) {
                addStudentRecord(student);
                return true;
            } else {
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public Optional<Student> getStudentById(String id) throws IOException {
        try{
            String query = String.format(Constants.SELECT_ALL_FROM_STUDENTS, id);
            Connection connection = getMyConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            rs.first();
            Student student = new Student();
            student.setId(rs.getString("id"));
            student.setName(rs.getString("name"));

            statement.close();
            connection.close();
            return Optional.of(student);
        } catch (Exception e){
            return Optional.empty();
        }
    }
}
