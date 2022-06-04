package com.mycompany;

public class Constants {

    public static final String SQL_CREATE_BOYS_TABLE = "CREATE TABLE IF NOT EXISTS BOYS("+
    "ID BIGSERIAL PRIMARY KEY,"+
    "NAME VARCHAR(20) NOT NULL);";
    
    public static final String SQL_CREATE_GIRLS_TABLE = "CREATE TABLE IF NOT EXISTS GIRLS("+
    "ID BIGSERIAL PRIMARY KEY,"+
    "NAME VARCHAR(20) NOT NULL);"; 
    
    public static final String SQL_CREATE_PAIR_TABLE = "CREATE TABLE IF NOT EXISTS PAIRS(" +
    "ID BIGSERIAL PRIMARY KEY,"+
    "BOY_ID BIGINT NOT NULL,"+
    "GIRL_ID BIGINT NOT NULL,"+
    "UNIQUE(BOY_ID,GIRL_ID));";

    public static final String SQL_DROP_BOYS_TABLE = "DROP TABLE IF EXISTS BOYS";

    public static final String JDBC_DRIVER = "jdbc_driver";
    public static final String JDBC_URL = "jdbc_url";
    public static final String JDBC_USER = "jdbc_user";
    public static final String JDBC_PASSWORD = "jdbc_password";

    public static final String SQL_Boy_INSERT = "INSERT INTO BOYS (NAME) VALUES('%s')";
    public static final String SQL_Boy_UPDATE_BY_ID = "UPDATE BOYS SET NAME='%s'  WHERE ID=%d";
    public static final String SQL_Boy_SELECT_ALL = "SELECT * FROM BOYS";
    public static final String SQL_Boy_SELECT_BY_ID = "SELECT * FROM BOYS WHERE ID=%d";
    public static final String SQL_Boy_DELETE_BY_ID = "DELETE FROM BOYS WHERE ID=%d";
    public static final String SQL_Boy_DROP_TABLE = "DROP TABLE IF EXISTS BOYS";
   
    public static final String SQL_Girl_INSERT = "INSERT INTO GIRLS (NAME) VALUES('%s')";
    public static final String SQL_Girl_UPDATE_BY_ID = "UPDATE GIRLS SET NAME='%s'  WHERE ID=%d";
    public static final String SQL_Girl_SELECT_ALL = "SELECT * FROM GIRLS";
    public static final String SQL_Girl_SELECT_BY_ID = "SELECT * FROM GIRLS WHERE ID=%d";
    public static final String SQL_Girl_DELETE_BY_ID = "DELETE FROM GIRLS WHERE ID=%d";
    public static final String SQL_Girl_DROP_TABLE = "DROP TABLE IF EXISTS GIRLS";

    public static final String SQL_PAIR_INSERT = "INSERT INTO PAIRS (BOY_ID,GIRL_ID) VALUES('%d' , '%d')";
}
