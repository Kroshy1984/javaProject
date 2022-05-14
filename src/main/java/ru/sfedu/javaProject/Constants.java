package ru.sfedu.javaProject;

public class Constants {

    // JDBC
    public static final String JDBC_DRIVER = "jdbc_driver";
    public static final String JDBC_URL = "jdbc_url";
    public static final String JDBC_BOY = "jdbc_boy";
    public static final String JDBC_GIRL = "jdbc_girl";
    public static final String JDBC_PASSWORD = "jdbc_password";

    //SQL
    
    //BOY
    public static final String SQL_BOY_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS t_boys (" +
            "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(25) NOT NULL," +
            "age INT NOT NULL)";
    public static final String SQL_BOY_INSERT = "INSERT INTO t_boys (name, age) VALUES('%s', '%d')";
    public static final String SQL_BOY_UPDATE_BY_ID = "UPDATE t_boys SET name='%s', age='%d' WHERE id=%d";
    public static final String SQL_BOY_SELECT_ALL = "SELECT * FROM t_boys";
    public static final String SQL_BOY_SELECT_BY_ID = "SELECT * FROM t_boys WHERE id=%d";
    public static final String SQL_BOY_DELETE_BY_ID = "DELETE FROM t_boys WHERE id=%d";
    public static final String SQL_BOY_DROP_TABLE = "DROP TABLE IF EXISTS t_boys";

    //GIRL
    public static final String SQL_GIRL_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS t_girls (" +
            "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(25) NOT NULL," +
            "age INT NOT NULL)";
    public static final String SQL_GIRL_INSERT = "INSERT INTO t_girls (name, age) VALUES('%s', '%d')";
    public static final String SQL_GIRL_UPDATE_BY_ID = "UPDATE t_girls SET name='%s', age='%d' WHERE id=%d";
    public static final String SQL_GIRL_SELECT_ALL = "SELECT * FROM t_girls";
    public static final String SQL_GIRL_SELECT_BY_ID = "SELECT * FROM t_girls WHERE id=%d";
    public static final String SQL_GIRL_DELETE_BY_ID = "DELETE FROM t_girls WHERE id=%d";
    public static final String SQL_GIRL_DROP_TABLE = "DROP TABLE IF EXISTS t_girls";

    //PAIR
    public static final String SQL_PAIR_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS pairs (" +
            "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            "b_id BIGINT NOT NULL," +
            "g_id BIGINT NOT NULL" +
            "UNIQUE(b_id, g_id))";
    public static final String SQL_PAIR_INSERT = "INSERT INTO pairs (b_id, g_id) VALUES('%d', '%d')";
    public static final String SQL_PAIR_UPDATE_BY_ID = "UPDATE pairs SET b_id='%d', g_id='%d' WHERE id=%d";
    public static final String SQL_PAIR_SELECT_ALL = "SELECT * FROM pairs";
    public static final String SQL_PAIR_SELECT_BY_ID = "SELECT * FROM pairs WHERE id=%d";
    public static final String SQL_PAIR_DELETE_BY_ID = "DELETE FROM pairs WHERE id=%d";
    public static final String SQL_PAIR_DROP_TABLE = "DROP TABLE IF EXISTS pairs";
}