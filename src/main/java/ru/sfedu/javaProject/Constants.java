package ru.sfedu.javaProject;

public class Constants {

    // JDBC
    public static final String JDBC_DRIVER = "jdbc_driver";
    public static final String JDBC_URL = "jdbc_url";
    public static final String JDBC_USER = "jdbc_user";
    public static final String JDBC_PASSWORD = "jdbc_password";

    //SQL
    public static final String SQL_USER_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS customers (" +
            "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            "firstname VARCHAR(25)," +
            "surname VARCHAR(25), " +
            "patronymic VARCHAR(25))";
    public static final String SQL_USER_INSERT = "INSERT INTO customers (firstname, surname, patronymic) VALUES('%s', '%s', '%s')";
    public static final String SQL_USER_UPDATE_BY_ID = "UPDATE customers SET firstname='%s', surname='%s', patronymic='%s' WHERE id=%d";
    public static final String SQL_USER_SELECT_ALL = "SELECT * FROM customers";
    public static final String SQL_USER_SELECT_BY_ID = "SELECT * FROM customers WHERE id=%d";
    public static final String SQL_USER_DELETE_BY_ID = "DELETE FROM customers WHERE id=%d";
    public static final String SQL_USER_DROP_TABLE = "DROP TABLE IF EXISTS customers";

}