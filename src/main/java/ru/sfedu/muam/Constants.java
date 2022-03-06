package ru.sfedu.muam;

public class Constants {
    public static final String JDBC_DRIVER = "jdbc_driver";
    public static final String JDBC_NAME = "h2database";
    public static final String JDBC_PATH = "jdbc_path";
    public static final String JDBC_URL = "jdbc_url";
    public static final String JDBC_USER = "jdbc_user";
    public static final String JDBC_PASSWORD = "jdbc_password";

    public final static String DROP_STUDENTS_TABLE = "DROP TABLE IF EXISTS STUDENTS";

    public static final String CREATE_TABLE_STUDENTS =
            "CREATE TABLE IF NOT EXISTS STUDENTS (" +
                    "id VARCHAR, " +
                    "name VARCHAR)";

    public static final String SELECT_ALL_FROM_STUDENTS = "SELECT * FROM STUDENTS";
    public static final String INSERT_INTO_STUDENTS = "INSERT INTO STUDENTS VALUES ('%s','%s')";
    public static final String SELECT_FROM_STUDENTS = "SELECT * FROM STUDENTS WHERE id = '%s'";
    public static final String DELETE_FROM_STUDENTS = "DELETE FROM STUDENTS WHERE id = '%s'";
}
