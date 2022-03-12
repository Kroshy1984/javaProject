package ru.sfedu.javaProject.api;


import ru.sfedu.javaProject.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DataProvider {

    //User
    void createUser(User user) throws SQLException, IOException;

    List<User> getAllUsers() throws SQLException, IOException;

    Optional<User> getUserById(Long id) throws SQLException, IOException;

    void updateUser(User user) throws SQLException, IOException;

    void deleteUserById(Long id) throws SQLException, IOException;

    //UserDocument
    void createUserDocument(UserDocument userDocument) throws SQLException, IOException;

    List<UserDocument> getAllUserDocuments() throws SQLException, IOException;

    Optional<UserDocument> getUserDocumentById(Long id) throws SQLException, IOException;

    void updateUserDocument(UserDocument userDocument) throws SQLException, IOException;

    void deleteUserDocumentById(Long id) throws SQLException, IOException;

    //Hotel
    void createHotel(Hotel hotel) throws SQLException, IOException;

    List<Hotel> getAllHotels() throws SQLException, IOException;

    Optional<Hotel> getHotelById(Long id) throws SQLException, IOException;

    void updateHotel(Hotel hotel) throws SQLException, IOException;

    void deleteHotelById(Long id) throws SQLException, IOException;
    
    //Room
    void createRoom(Room room) throws SQLException, IOException;

    List<Room> getAllRooms() throws SQLException, IOException;

    Optional<Room> getRoomById(Long id) throws SQLException, IOException;

    void updateRoom(Room room) throws SQLException, IOException;

    void deleteRoomById(Long id) throws SQLException, IOException;
    
    //Booking
    void createBooking(Booking booking) throws SQLException, IOException;

    List<Booking> getAllBookings() throws SQLException, IOException;

    Optional<Booking> getBookingById(Long id) throws SQLException, IOException;

    void updateBooking(Booking booking) throws SQLException, IOException;

    void deleteBookingById(Long id) throws SQLException, IOException;

}
