package ru.sfedu.FatCalculator;

public class Constants {
    public static final String DEFAULT_CONFIG_PATH = "/main/resources/enviroment.properties";

    public static final String HCFG_PATH = "hibernate_cfg_path";

    public static final String UNKNOWN_SOURCE = "unknown";

    public static final String RESULT_CSV_PATH = "result_csv_path";
    public static final String USER_CSV_PATH = "user_csv_path";
    public static final String TAPE_CSV_PATH = "tape_csv_path";
    public static final String THREESITESKINFOLD_CSV_PATH = "threesiteskinfold_csv_path";
    public static final String FOURSITESKINFOLD_CSV_PATH = "foursiteskinfold_csv_path";

    public static final String RESULT_XML_PATH = "result_xml_path";
    public static final String USER_XML_PATH = "user_xml_path";
    public static final String TAPE_XML_PATH = "tape_xml_path";
    public static final String THREESITESKINFOLD_XML_PATH = "threesiteskinfold_xml_path";
    public static final String FOURSITESKINFOLD_XML_PATH = "foursiteskinfold_xml_path";

    public static final String MONGO_HOST_NAME = "mongo_host";
    public static final String MONGO_DB_NAME = "mongo_db";
    public static final String MONGO_ACTOR = "system";

    public static final String H2_URL = "jdbc:h2:~/FatDB";
    public static final String H2_USER_NAME = "user";
    public static final String H2_USER_PASSWORD = "1317";

    public static final String CREATE_TABLE_USER =
            "CREATE TABLE IF NOT EXISTS USER (" +
                    "userid IDENTITY, " +
                    "name VARCHAR," +
                    "surname VARCHAR)";

    public static final String CREATE_TABLE_RESULT =
            "CREATE TABLE IF NOT EXISTS RESULT (" +
                    "result_id IDENTITY, " +
                    "userid INT, " +
                    "date VARCHAR," +
                    "fat_percentage FLOAT(2)," +
                    "stage ENUM('UNDERWEIGHT', 'HEAlTHY', 'OVERWEIGHT', 'OBESE', 'EXTREMELY_OBESE')default 'none')";


    public static final String CREATE_TABLE_TAPE =
            "CREATE TABLE IF NOT EXISTS TAPE (" +
                    "measurement_id IDENTITY," +
                    "userid INT," +
                    "weight FLOAT(2)," +
                    "height FLOAT(2)," +
                    "age INT," +
                    "gender VARCHAR," +
                    "neck FLOAT(2)," +
                    "waist FLOAT(2)," +
                    "hips FLOAT(2)," +
                    "abdomen FLOAT(2))";

    public static final String CREATE_TABLE_THREESITESKINFOLD =
            "CREATE TABLE IF NOT EXISTS THREESITESKINFOLD (" +
                    "measurement_id IDENTITY," +
                    "userid INT," +
                    "weight FLOAT(2)," +
                    "height FLOAT(2)," +
                    "age INT," +
                    "gender VARCHAR," +
                    "chest FLOAT(2)," +
                    "thigh FLOAT(2)," +
                    "tricep FLOAT(2)," +
                    "suprailiac FLOAT(2)," +
                    "abdominal FLOAT(2))";

    public static final String CREATE_TABLE_FOURSITESKINFOLD =
            "CREATE TABLE IF NOT EXISTS FOURSITESKINFOLD (" +
                    "measurement_id IDENTITY," +
                    "userid INT," +
                    "weight FLOAT(2)," +
                    "height FLOAT(2)," +
                    "age INT," +
                    "gender VARCHAR," +
                    "thigh FLOAT(2)," +
                    "tricep FLOAT(2)," +
                    "suprailiac FLOAT(2)," +
                    "abdominal FLOAT(2))";


    public final static String DROP_USER_TABLE = "DROP TABLE IF EXISTS USER";
    public final static String DROP_RESULT_TABLE = "DROP TABLE IF EXISTS RESULT";
    public final static String DROP_TAPE_TABLE = "DROP TABLE IF EXISTS TAPE";
    public final static String DROP_THREESITESKINFOLD_TABLE = "DROP TABLE IF EXISTS THREESITESKINFOLD";
    public final static String DROP_FOURSITESKINFOLD_TABLE = "DROP TABLE IF EXISTS FOURSITESKINFOLD";

    public static final String INSERT_INTO_USER = "INSERT INTO USER VALUES(%s, '%s', '%s')";
    public static final String SELECT_FROM_USER = "SELECT * FROM USER WHERE USERID = %s";
    public static final String SELECT_FROM_USER_ALL = "SELECT * FROM USER";
    public static final String DELETE_FROM_USER = "DELETE FROM USER WHERE USERID = %s";

    public static final String INSERT_INTO_RESULT = "INSERT INTO RESULT VALUES(%s, '%s', '%s', '%s', '%s')";
    public static final String SELECT_FROM_RESULT = "SELECT * FROM RESULT WHERE RESULT_ID = %s";
    public static final String SELECT_FROM_RESULT_ALL = "SELECT * FROM RESULT";
    public static final String SELECT_FROM_RESULT_ALL_USER = "SELECT * FROM RESULT WHERE USERID = %s";
    public static final String DELETE_FROM_RESULT = "DELETE FROM RESULT WHERE RESULT_ID = %s";

    public static final String INSERT_INTO_TAPE = "INSERT INTO TAPE VALUES(%s, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
    public static final String SELECT_FROM_TAPE = "SELECT * FROM TAPE WHERE MEASUREMENT_ID = %s";
    public static final String SELECT_FROM_TAPE_ALL = "SELECT * FROM TAPE";
    public static final String DELETE_FROM_TAPE = "DELETE FROM TAPE WHERE MEASUREMENT_ID = %s";

    public static final String INSERT_INTO_THREESITESKINFOLD = "INSERT INTO THREESITESKINFOLD VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
    public static final String SELECT_FROM_THREESITESKINFOLD = "SELECT * FROM THREESITESKINFOLD WHERE MEASUREMENT_ID = %s";
    public static final String SELECT_FROM_THREESITESKINFOLD_ALL = "SELECT * FROM THREESITESKINFOLD";
    public static final String DELETE_FROM_THREESITESKINFOLD = "DELETE FROM THREESITESKINFOLD WHERE MEASUREMENT_ID = %s";

    public static final String INSERT_INTO_FOURSITESKINFOLD = "INSERT INTO FOURSITESKINFOLD VALUES(%s, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
    public static final String SELECT_FROM_FOURSITESKINFOLD = "SELECT * FROM FOURSITESKINFOLD WHERE MEASUREMENT_ID = %s";
    public static final String SELECT_FROM_FOURSITESKINFOLD_ALL = "SELECT * FROM FOURSITESKINFOLD";
    public static final String DELETE_FROM_FOURSITESKINFOLD = "DELETE FROM FOURSITESKINFOLD WHERE MEASUREMENT_ID = %s";

    public static final String ADD_USER = "au";
    public static final String DELETE_USER = "du";
    public static final String GET_USER = "gu";
    public static final String GET_ALL_USERS = "gau";
    public static final String UPDATE_USER = "uu";
    
    public static final String ADD_RESULT = "ar";
    public static final String DELETE_RESULT = "dr";
    public static final String GET_RESULT = "gr";
    public static final String GET_ALL_RESULTS = "gar";
    public static final String UPDATE_RESULT = "ur";

    public static final String ADD_TAPE = "at";
    public static final String DELETE_TAPE = "dt";
    public static final String GET_TAPE = "gt";
    public static final String GET_ALL_TAPES = "gat";
    public static final String UPDATE_TAPE = "ut";

    public static final String ADD_THREESITESKINFOLD = "a3";
    public static final String DELETE_THREESITESKINFOLD = "d3";
    public static final String GET_THREESITESKINFOLD = "g3";
    public static final String GET_ALL_THREESITESKINFOLDS = "ga3";
    public static final String UPDATE_THREESITESKINFOLD = "u3";

    public static final String ADD_FOURSITESKINFOLD = "a4";
    public static final String DELETE_FOURSITESKINFOLD = "d4";
    public static final String GET_FOURSITESKINFOLD = "g4";
    public static final String GET_ALL_FOURSITESKINFOLDS = "ga4";
    public static final String UPDATE_FOURSITESKINFOLD = "u4";

    public static final String MEASURE_FAT = "mf";
    public static final String ANALYSIS = "a";

    public static final String GET_SIZE_DB = "SELECT pg_size_pretty( pg_database_size( 'FatDB' ) );";
    public static final String GET_TABLES_DB = "SELECT table_name FROM information_schema.tables\n" +
            "    WHERE table_schema NOT IN ('information_schema','pg_catalog');";
    public static final String GET_USERS_DB = "select usename from pg_shadow;";
    public static final String GET_COLUMNS_DB = "    select table_name, column_name from information_schema.columns" +
            "    where table_catalog = 'FatDB' and table_schema = 'public' order by table_name desc;";

}
