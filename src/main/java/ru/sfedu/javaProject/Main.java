package ru.sfedu.javaProject;

import ru.sfedu.javaProject.api.DataProvider;
import ru.sfedu.javaProject.api.DataProviderJDBC;
import ru.sfedu.javaProject.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static User createUser(String firstname, String surname, String patronymic, Long passportId, Long drivingLicenseId, Long diplomaId) {
        User user = new User();
        user.setFirstname(firstname);
        user.setSurname(surname);
        user.setPatronymic(patronymic);
        user.setPassportId(passportId);
        user.setDrivingLicenseId(drivingLicenseId);
        user.setDiplomaId(diplomaId);
        return user;
    }

    private static UserDocument createUserDocument(UserDocument.UserDocumentType type, String serialNumber) {
        UserDocument userDocument = new UserDocument();
        userDocument.setType(type);
        userDocument.setSerialNumber(serialNumber);
        return userDocument;
    }

    private static Hotel createHotel(String name) {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        return hotel;
    }

    private static Room createRoom(int number, Long hotelId) {
        Room room = new Room();
        room.setNumber(number);
        room.setHotelId(hotelId);
        return room;
    }

    private static Booking createBooking(Long userId, Long roomId, LocalDate date) {
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setRoomId(roomId);
        booking.setDate(date);
        return booking;
    }

    public static void main(String[] args) {

        List<UserDocument> userDocumentList = Arrays.asList(
                createUserDocument(UserDocument.UserDocumentType.PASSPORT, "p1"),
                createUserDocument(UserDocument.UserDocumentType.DRIVING_LICENSE, "l1"),
                createUserDocument(UserDocument.UserDocumentType.DIPLOMA, "d1"),
                createUserDocument(UserDocument.UserDocumentType.PASSPORT, "p2"),
                createUserDocument(UserDocument.UserDocumentType.DRIVING_LICENSE, "l2"),
                createUserDocument(UserDocument.UserDocumentType.DIPLOMA, "d2"));

        List<User> userList = Arrays.asList(
                createUser("Дмитрий", "Харатян", "Александрович", 1L, 2L, 3L),
                createUser("Иван", "Иванов", "Иванович", 4L, 5L, 6L));

        List<Hotel> hotelList = Arrays.asList(
                createHotel("Hotel 1"),
                createHotel("Hotel 2"));

        List<Room> roomList = Arrays.asList(
                createRoom(101, 1L),
                createRoom(102, 1L),
                createRoom(201, 1L),
                createRoom(201, 2L),
                createRoom(202, 2L),
                createRoom(301, 2L));

        List<Booking> bookingList = Arrays.asList(
                createBooking(1L, 1L, LocalDate.now()),
                createBooking(1L, 1L, LocalDate.now().plusDays(1)),
                createBooking(1L, 2L, LocalDate.now()),
                createBooking(2L, 5L, LocalDate.now()));

        try {
            DataProvider dataProvider = DataProviderJDBC.getInstance();
            DataProviderJDBC.getInstance().dropTables();

            dataProvider.createUserDocument(userDocumentList.get(0));
            dataProvider.createUserDocument(userDocumentList.get(1));
            dataProvider.createUserDocument(userDocumentList.get(2));
            dataProvider.createUserDocument(userDocumentList.get(3));
            dataProvider.createUserDocument(userDocumentList.get(4));
            dataProvider.createUserDocument(userDocumentList.get(5));

            dataProvider.createUser(userList.get(0));
            dataProvider.createUser(userList.get(1));

            dataProvider.createHotel(hotelList.get(0));
            dataProvider.createHotel(hotelList.get(1));

            dataProvider.createRoom(roomList.get(0));
            dataProvider.createRoom(roomList.get(1));
            dataProvider.createRoom(roomList.get(2));
            dataProvider.createRoom(roomList.get(3));
            dataProvider.createRoom(roomList.get(4));
            dataProvider.createRoom(roomList.get(5));

            dataProvider.createBooking(bookingList.get(0));
            dataProvider.createBooking(bookingList.get(1));
            dataProvider.createBooking(bookingList.get(2));
            dataProvider.createBooking(bookingList.get(3));

            System.out.println(dataProvider.getAllUsers());
            System.out.println(dataProvider.getAllUserDocuments());
            System.out.println(dataProvider.getAllHotels());
            System.out.println(dataProvider.getAllRooms());
            System.out.println(dataProvider.getAllBookings());
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

}
