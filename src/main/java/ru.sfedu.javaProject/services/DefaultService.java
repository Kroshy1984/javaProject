package ru.sfedu.javaProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.javaProject.model.entity.Appointment;
import ru.sfedu.javaProject.model.entity.Pair;
import ru.sfedu.javaProject.model.entity.User;
import ru.sfedu.javaProject.repositories.AppointmentRepository;
import ru.sfedu.javaProject.repositories.PairRepository;
import ru.sfedu.javaProject.repositories.UserRepository;

import java.util.Optional;

@Service
public class DefaultService {

    private final UserRepository userRepository;
    private final PairRepository pairRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    DefaultService(UserRepository userRepository, PairRepository pairRepository, AppointmentRepository appointmentRepository) {
        this.userRepository = userRepository;
        this.pairRepository = pairRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public boolean createUser(User user) {
        if (user.getId() != null && userRepository.existsById(user.getId())) return false;
        userRepository.save(user);
        return true;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Iterable<User> getAllUsersById(Iterable<Long> idList) {
        return userRepository.findAllById(idList);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean updateUser(User user) {
        if (!userRepository.existsById(user.getId())) return false;
        userRepository.save(user);
        return true;
    }

    public boolean deleteUserById(Long id) {
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public boolean createPair(Pair pair) throws IllegalArgumentException {
        if ((pair.getId() != null && pairRepository.existsById(pair.getId()))
                || pair.getUsers().size() != 2
                || pairRepository.existsByUsersIn(pair.getUsers())) return false;
        pairRepository.save(pair);
        return true;
    }

    public Optional<Pair> getPairById(Long id) {
        return pairRepository.findById(id);
    }

    public Iterable<Pair> getAllPairs() {
        return pairRepository.findAll();
    }

    public Iterable<Pair> getAllPairsByUser(User user) {
        return pairRepository.findAllByUsersContains(user);
    }

    public boolean updatePair(Pair pair) {
        if (!pairRepository.existsById(pair.getId())) return false;
        pairRepository.save(pair);
        return true;
    }

    public boolean deletePairById(Long id) {
        if (!pairRepository.existsById(id)) return false;
        pairRepository.deleteById(id);
        return true;
    }

    public void deleteAllPairs() {
        pairRepository.deleteAll();
    }

    public boolean createAppointment(Appointment appointment) {
        if (appointment.getId() != null && appointmentRepository.existsById(appointment.getId())) return false;
        appointmentRepository.save(appointment);
        return true;
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Iterable<Appointment> getAllAppointmentsById(Iterable<Long> idList) {
        return appointmentRepository.findAllById(idList);
    }

    public Iterable<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public boolean updateAppointment(Appointment appointment) {
        if (!appointmentRepository.existsById(appointment.getId())) return false;
        appointmentRepository.save(appointment);
        return true;
    }

    public boolean deleteAppointmentById(Long id) {
        if (!appointmentRepository.existsById(id)) return false;
        appointmentRepository.deleteById(id);
        return true;
    }

    public void deleteAllAppointments() {
        appointmentRepository.deleteAll();
    }
    
}
