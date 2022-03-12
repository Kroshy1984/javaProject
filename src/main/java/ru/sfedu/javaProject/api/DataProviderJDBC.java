package ru.sfedu.javaProject.api;

import ru.sfedu.javaProject.Constants;
import ru.sfedu.javaProject.model.*;
import ru.sfedu.javaProject.utils.ConfigurationUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
        return DriverManager.getConnection(
                ConfigurationUtil.getConfigurationEntry(Constants.JDBC_URL),
                ConfigurationUtil.getConfigurationEntry(Constants.JDBC_USER),
                ConfigurationUtil.getConfigurationEntry(Constants.JDBC_PASSWORD));
    }

    private void createTables() throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(Constants.SQL_USER_DOCUMENT_TABLE_CREATE);
        connection.createStatement().executeUpdate(Constants.SQL_USER_TABLE_CREATE);
        connection.createStatement().executeUpdate(Constants.SQL_HOTEL_TABLE_CREATE);
        connection.createStatement().executeUpdate(Constants.SQL_ROOM_TABLE_CREATE);
        connection.createStatement().executeUpdate(Constants.SQL_BOOKING_TABLE_CREATE);
        connection.close();
    }

    public void dropTables() throws SQLException, IOException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate(Constants.SQL_BOOKING_DROP_TABLE);
        connection.createStatement().executeUpdate(Constants.SQL_ROOM_DROP_TABLE);
        connection.createStatement().executeUpdate(Constants.SQL_HOTEL_DROP_TABLE);
        connection.createStatement().executeUpdate(Constants.SQL_USER_DROP_TABLE);
        connection.createStatement().executeUpdate(Constants.SQL_USER_DOCUMENT_DROP_TABLE);
        connection.close();
        createTables();
    }

    private List<User> getUserListFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong(1));
            user.setFirstname(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setPatronymic(resultSet.getString(4));
            user.setPassportId(resultSet.getLong(5));
            user.setDrivingLicenseId(resultSet.getLong(6));
            user.setDiplomaId(resultSet.getLong(7));
            userList.add(user);
        }
        return userList;
    }

    private List<UserDocument> getUserDocumentListFromResultSet(ResultSet resultSet) throws SQLException {
        List<UserDocument> userDocumentList = new ArrayList<>();
        while (resultSet.next()) {
            UserDocument userDocument = new UserDocument();
            userDocument.setId(resultSet.getLong(1));
            userDocument.setType(UserDocument.UserDocumentType.valueOf(resultSet.getString(2)));
            userDocument.setSerialNumber(resultSet.getString(3));
            userDocumentList.add(userDocument);
        }
        return userDocumentList;
    }

    private List<Hotel> getHotelListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Hotel> hotelList = new ArrayList<>();
        while (resultSet.next()) {
            Hotel hotel = new Hotel();
            hotel.setId(resultSet.getLong(1));
            hotel.setName(resultSet.getString(2));
            hotelList.add(hotel);
        }
        return hotelList;
    }

    private List<Room> getRoomListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Room> roomList = new ArrayList<>();
        while (resultSet.next()) {
            Room room = new Room();
            room.setId(resultSet.getLong(1));
            room.setNumber(resultSet.getInt(2));
            room.setHotelId(resultSet.getLong(3));
            roomList.add(room);
        }
        return roomList;
    }

    private List<Booking> getBookingListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        while (resultSet.next()) {
            Booking booking = new Booking();
            booking.setId(resultSet.getLong(1));
            booking.setUserId(resultSet.getLong(2));
            booking.setRoomId(resultSet.getLong(3));
            booking.setDate(resultSet.getObject(4, LocalDate.class));
            bookingList.add(booking);
        }
        return bookingList;
    }

    //User
    @Override
    public void createUser(User user) throws SQLException, IOException {
        getConnection().createStatement().executeUpdate(String.format(Constants.SQL_USER_INSERT,
                user.getFirstname(), user.getSurname(), user.getPatronymic(), user.getPassportId(), user.getDrivingLicenseId(), user.getDiplomaId()));
    }

    @Override
    public Optional<User> getUserById(Long id) throws SQLException, IOException {
        return getUserListFromResultSet(getConnection().prepareStatement(String.format(Constants.SQL_USER_SELECT_BY_ID, id)).executeQuery())
                .stream().findAny();
    }

    @Override
    public List<User> getAllUsers() throws SQLException, IOException {
        return getUserListFromResultSet(getConnection().prepareStatement(Constants.SQL_USER_SELECT_ALL).executeQuery());
    }

    @Override
    public void updateUser(User user) throws SQLException, IOException {
        getConnection().createStatement().executeUpdate(String.format(Constants.SQL_USER_UPDATE_BY_ID,
                user.getFirstname(), user.getSurname(), user.getPatronymic(), user.getPassportId(), user.getDrivingLicenseId(), user.getDiplomaId(), user.getId()));
    }

    @Override
    public void deleteUserById(Long id) throws SQLException, IOException {
        getConnection().createStatement().executeUpdate(String.format(Constants.SQL_USER_DELETE_BY_ID, id));
    }

    //UserDocument
    @Override
    public void createUserDocument(UserDocument userDocument) throws SQLException, IOException {
        getConnection().createStatement().executeUpdate(String.format(Constants.SQL_USER_DOCUMENT_INSERT,
                userDocument.getType(), userDocument.getSerialNumber()));
    }

    @Override
    public Optional<UserDocument> getUserDocumentById(Long id) throws SQLException, IOException {
        return getUserDocumentListFromResultSet(getConnection().prepareStatement(String.format(Constants.SQL_USER_DOCUMENT_SELECT_BY_ID, id)).executeQuery())
                .stream().findAny();
    }

    @Override
    public List<UserDocument> getAllUserDocuments() throws SQLException, IOException {
        return getUserDocumentListFromResultSet(getConnection().prepareStatement(Constants.SQL_USER_DOCUMENT_SELECT_ALL).executeQuery());
    }

    @Override
    public void updateUserDocument(UserDocument userDocument) throws SQLException, IOException {
        getConnection().createStatement().executeUpdate(String.format(Constants.SQL_USER_DOCUMENT_UPDATE_BY_ID,
                userDocument.getType(), userDocument.getSerialNumber(), userDocument.getId()));
    }

    @Override
    public void deleteUserDocumentById(Long id) throws SQLException, IOException {
        getConnection().createStatement().executeUpdate(String.format(Constants.SQL_USER_DOCUMENT_DELETE_BY_ID, id));
    }

    //Hotel
    @Override
    public void createHotel(Hotel hotel) throws SQLException, IOException {
        getConnection().createStatement().executeUpdate(String.format(Constants.SQL_HOTEL_INSERT, hotel.getName()));
    }

    @Override
    public List<Hotel> getAllHotels() throws SQLException, IOException {
        return getHotelListFromResultSet(getConnection().prepareStatement(Constants.SQL_HOTEL_SELECT_ALL).executeQuery());
    }

    @Override
    public Optional<Hotel> getHotelById(Long id) throws SQLException, IOException {
        return getHotelListFromResultSet(getConnection().prepareStatement(String.format(Constants.SQL_HOTEL_SELECT_BY_ID, id)).executeQuery())
                .stream().findAny();
    }

    @Override
    public void updateHotel(Hotel hotel) throws SQLException, IOException {
        getConnection().createStatement().executeUpdate(String.format(Constants.SQL_HOTEL_UPDATE_BY_ID, hotel.getName(), hotel.getId()));
    }

    @Override
    public void deleteHotelById(Long id) throws SQLException, IOException {
        getConnection().createStatement().executeUpdate(String.format(Constants.SQL_HOTEL_DELETE_BY_ID, id));
    }

    //Room
    @Override
    public void createRoom(Room room) throws SQLException, IOException {
        getConnection().createStatement().executeUpdate(String.format(Constants.SQL_ROOM_INSERT, room.getNumber(), room.getHotelId()));
    }

    @Override
    public List<Room> getAllRooms() throws SQLException, IOException {
        return getRoomListFromResultSet(getConnection().prepareStatement(Constants.SQL_ROOM_SELECT_ALL).executeQuery());
    }

    @Override
    public Optional<Room> getRoomById(Long id) throws SQLException, IOException {
        return getRoomListFromResultSet(getConnection().prepareStatement(String.format(Constants.SQL_ROOM_SELECT_BY_ID, id)).executeQuery())
                .stream().findAny();
    }

    @Override
    public void updateRoom(Room room) throws SQLException, IOException {
        getConnection().createStatement().executeUpdate(String.format(Constants.SQL_ROOM_UPDATE_BY_ID, room.getNumber(), room.getHotelId(), room.getId()));
    }

    @Override
    public void deleteRoomById(Long id) throws SQLException, IOException {
        getConnection().createStatement().executeUpdate(String.format(Constants.SQL_ROOM_DELETE_BY_ID, id));
    }

    //Booking
    @Override
    public void createBooking(Booking booking) throws SQLException, IOException {
        getConnection().createStatement().executeUpdate(String.format(Constants.SQL_BOOKING_INSERT,
                booking.getUserId(), booking.getRoomId(), booking.getDate()));
    }

    @Override
    public List<Booking> getAllBookings() throws SQLException, IOException {
        return getBookingListFromResultSet(getConnection().prepareStatement(Constants.SQL_BOOKING_SELECT_ALL).executeQuery());
    }

    @Override
    public Optional<Booking> getBookingById(Long id) throws SQLException, IOException {
        return getBookingListFromResultSet(getConnection().prepareStatement(String.format(Constants.SQL_BOOKING_SELECT_BY_ID, id)).executeQuery())
                .stream().findAny();
    }

    @Override
    public void updateBooking(Booking booking) throws SQLException, IOException {
        getConnection().createStatement().executeUpdate(String.format(Constants.SQL_BOOKING_UPDATE_BY_ID,
                booking.getUserId(), booking.getRoomId(), booking.getDate(), booking.getId()));
    }

    @Override
    public void deleteBookingById(Long id) throws SQLException, IOException {
        getConnection().createStatement().executeUpdate(String.format(Constants.SQL_BOOKING_DELETE_BY_ID, id));
    }
}
