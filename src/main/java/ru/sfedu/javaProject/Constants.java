package ru.sfedu.javaProject;

public class Constants {

    // JDBC
    public static final String JDBC_DRIVER = "jdbc_driver";
    public static final String JDBC_URL = "jdbc_url";
    public static final String JDBC_USER = "jdbc_user";
    public static final String JDBC_PASSWORD = "jdbc_password";

    //SQL
    //User
    public static final String SQL_USER_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS customer (" +
            "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            "firstname VARCHAR(25)," +
            "surname VARCHAR(25), " +
            "patronymic VARCHAR(25), " +
            "passport_id BIGINT UNIQUE," +
            "driving_license_id BIGINT UNIQUE, " +
            "diploma_id BIGINT UNIQUE," +
            "CONSTRAINT customer_passport_id_fk FOREIGN KEY (passport_id) references user_document," +
            "CONSTRAINT customer_driving_license_id_fk FOREIGN KEY (driving_license_id) references user_document," +
            "CONSTRAINT customer_diploma_id_fk FOREIGN KEY (diploma_id) references user_document)";
    public static final String SQL_USER_INSERT = "INSERT INTO customer (firstname, surname, patronymic, passport_id, driving_license_id, diploma_id) VALUES('%s', '%s', '%s', %d, %d, %d)";
    public static final String SQL_USER_UPDATE_BY_ID = "UPDATE customer SET firstname='%s', surname='%s', patronymic='%s' passport_id=%d, drivingLicense_id=%d, diploma_id=%d WHERE id=%d";
    public static final String SQL_USER_SELECT_ALL = "SELECT * FROM customer";
    public static final String SQL_USER_SELECT_BY_ID = "SELECT * FROM customer WHERE id=%d";
    public static final String SQL_USER_DELETE_BY_ID = "DELETE FROM customer WHERE id=%d";
    public static final String SQL_USER_DROP_TABLE = "DROP TABLE IF EXISTS customer";

    //UserDocument
    public static final String SQL_USER_DOCUMENT_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS user_document (" +
            "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            "type VARCHAR(25) NOT NULL," +
            "serial_number VARCHAR(25) NOT NULL)";
    public static final String SQL_USER_DOCUMENT_INSERT = "INSERT INTO user_document (type, serial_number) VALUES('%s', '%s')";
    public static final String SQL_USER_DOCUMENT_UPDATE_BY_ID = "UPDATE user_document SET type='%s', serial_number='%s' WHERE id=%d";
    public static final String SQL_USER_DOCUMENT_SELECT_ALL = "SELECT * FROM user_document";
    public static final String SQL_USER_DOCUMENT_SELECT_BY_ID = "SELECT * FROM user_document WHERE id=%d";
    public static final String SQL_USER_DOCUMENT_DELETE_BY_ID = "DELETE FROM user_document WHERE id=%d";
    public static final String SQL_USER_DOCUMENT_DROP_TABLE = "DROP TABLE IF EXISTS user_document";

    //Hotel
    public static final String SQL_HOTEL_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS hotel (" +
            "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(25) NOT NULL)";
    public static final String SQL_HOTEL_INSERT = "INSERT INTO hotel (name) VALUES('%s')";
    public static final String SQL_HOTEL_UPDATE_BY_ID = "UPDATE hotel SET name='%s' WHERE id=%d";
    public static final String SQL_HOTEL_SELECT_ALL = "SELECT * FROM hotel";
    public static final String SQL_HOTEL_SELECT_BY_ID = "SELECT * FROM hotel WHERE id=%d";
    public static final String SQL_HOTEL_DELETE_BY_ID = "DELETE FROM hotel WHERE id=%d";
    public static final String SQL_HOTEL_DROP_TABLE = "DROP TABLE IF EXISTS hotel";

    //Room
    public static final String SQL_ROOM_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS room (" +
            "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            "number integer NOT NULL, " +
            "hotel_id BIGINT NOT NULL, " +
            "CONSTRAINT room_hotel_id_fk FOREIGN KEY (hotel_id) references hotel," +
            "CONSTRAINT room_unique1 UNIQUE (number, hotel_id))";
    public static final String SQL_ROOM_INSERT = "INSERT INTO room (number, hotel_id) VALUES(%d, %d)";
    public static final String SQL_ROOM_UPDATE_BY_ID = "UPDATE room SET number=%d, hotel_id=%d WHERE id=%d";
    public static final String SQL_ROOM_SELECT_ALL = "SELECT * FROM room";
    public static final String SQL_ROOM_SELECT_BY_ID = "SELECT * FROM room WHERE id=%d";
    public static final String SQL_ROOM_DELETE_BY_ID = "DELETE FROM room WHERE id=%d";
    public static final String SQL_ROOM_DROP_TABLE = "DROP TABLE IF EXISTS room";

    //Booking
    public static final String SQL_BOOKING_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS booking (" +
            "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            "user_id BIGINT NOT NULL, " +
            "room_id BIGINT NOT NULL, " +
            "date DATE NOT NULL, " +
            "CONSTRAINT booking_user_id_fk FOREIGN KEY (user_id) references customer, " +
            "CONSTRAINT booking_room_id_fk FOREIGN KEY (room_id) references room, " +
            "CONSTRAINT booking_unique1 UNIQUE (room_id, date))";
    public static final String SQL_BOOKING_INSERT = "INSERT INTO booking (user_id, room_id, date) VALUES('%s', '%s', '%s')";
    public static final String SQL_BOOKING_UPDATE_BY_ID = "UPDATE booking SET user_id='%s', room_id='%s', date='%s' WHERE id=%d";
    public static final String SQL_BOOKING_SELECT_ALL = "SELECT * FROM booking";
    public static final String SQL_BOOKING_SELECT_BY_ID = "SELECT * FROM booking WHERE id=%d";
    public static final String SQL_BOOKING_DELETE_BY_ID = "DELETE FROM booking WHERE id=%d";
    public static final String SQL_BOOKING_DROP_TABLE = "DROP TABLE IF EXISTS booking";


}