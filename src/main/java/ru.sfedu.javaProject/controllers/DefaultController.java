package ru.sfedu.javaProject.controllers;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.javaProject.Constants;
import ru.sfedu.javaProject.model.dto.PairRequest;
import ru.sfedu.javaProject.model.entity.Appointment;
import ru.sfedu.javaProject.model.entity.Pair;
import ru.sfedu.javaProject.model.entity.User;
import ru.sfedu.javaProject.services.DefaultService;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.validation.Valid;
import java.util.*;

@Controller
@Validated
public class DefaultController {

    private DefaultService defaultService;

    @Autowired
    DefaultController(DefaultService defaultService) {
        this.defaultService = defaultService;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    private Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EntityNotFoundException.class, EntityExistsException.class})
    @ResponseBody
    private String handleEntityNotFoundOrExistsExceptions(
            PersistenceException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    private String handleConstraintViolationExceptions(
            ConstraintViolationException ex) {
        return Constants.ERROR_CONSTRAINT_VIOLATION;
    }

    @PostMapping(path = {"user/create/"})
    @ResponseBody
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user) {
        user.setId(null);
        defaultService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = {"user/{id}"})
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(
                defaultService.getUserById(id).orElseThrow(() ->
                        new EntityNotFoundException(Constants.ERROR_USER_NOT_FOUND)),
                HttpStatus.OK);
    }

    @GetMapping(path = {"user/all"})
    @ResponseBody
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> users = defaultService.getAllUsers();
        if (!users.iterator().hasNext()) throw new EntityNotFoundException(Constants.ERROR_USER_NOT_FOUND);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(path = {"user/update/"})
    @ResponseBody
    public ResponseEntity<Void> updateUser(@Valid @RequestBody User user) {
        if (!defaultService.updateUser(user)) throw new EntityExistsException(Constants.ERROR_USER_NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"user/{id}"})
    @ResponseBody
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!defaultService.deleteUserById(id)) throw new EntityExistsException(Constants.ERROR_USER_NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"user/all"})
    @ResponseBody
    public ResponseEntity<Void> deleteAllUsers() {
        defaultService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = {"pair/create/"})
    @ResponseBody
    public ResponseEntity<Void> createPair(@Valid @RequestBody PairRequest pairRequest) {
        Pair pair = new Pair();
        Set<User> userSet = new HashSet<>();
        defaultService.getAllUsersById(List.of(pairRequest.getFirstUserId(), pairRequest.getSecondUserId())).forEach(userSet::add);
        pair.setUsers(userSet);
        if (!defaultService.createPair(pair)) throw new EntityExistsException(Constants.ERROR_PAIR_IS_EXISTS);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = {"pair/{id}"})
    @ResponseBody
    public ResponseEntity<Pair> getPair(@PathVariable Long id) {
        return new ResponseEntity<>(
                defaultService.getPairById(id).orElseThrow(() -> new EntityNotFoundException(Constants.ERROR_PAIR_NOT_FOUND)),
                HttpStatus.OK);
    }

    @GetMapping(path = {"pair/all"})
    @ResponseBody
    public ResponseEntity<Iterable<Pair>> getAllPairs() {
        Iterable<Pair> pairs = defaultService.getAllPairs();
        if (!pairs.iterator().hasNext()) throw new EntityNotFoundException(Constants.ERROR_PAIR_NOT_FOUND);
        return new ResponseEntity<>(pairs, HttpStatus.OK);
    }

    @GetMapping(path = {"pair/getByUserId"})
    @ResponseBody
    public ResponseEntity<Iterable<Pair>> getAllPairsByUser(@RequestParam(name = "id") Long id) {
        Iterable<Pair> pairs = defaultService.getAllPairsByUser(defaultService.getUserById(id)
                .orElseThrow(() -> new EntityNotFoundException(Constants.ERROR_USER_NOT_FOUND)));
        if (!pairs.iterator().hasNext()) throw new EntityNotFoundException(Constants.ERROR_PAIR_NOT_FOUND);
        return new ResponseEntity<>(pairs, HttpStatus.OK);
    }

    @PostMapping(path = {"pair/update/"})
    @ResponseBody
    public ResponseEntity<Void> updatePair(@Valid @RequestBody PairRequest pairRequest) {
        Pair pair = new Pair();
        Set<User> userSet = new HashSet<>();
        defaultService.getAllUsersById(List.of(pairRequest.getFirstUserId(), pairRequest.getSecondUserId())).forEach(userSet::add);
        pair.setUsers(userSet);
        if (!defaultService.updatePair(pair)) throw new EntityExistsException(Constants.ERROR_PAIR_IS_EXISTS);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"pair/{id}"})
    @ResponseBody
    public ResponseEntity<Void> deletePair(@PathVariable Long id) {
        if (!defaultService.deletePairById(id)) throw new EntityExistsException(Constants.ERROR_PAIR_NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"pair/all"})
    @ResponseBody
    public ResponseEntity<Void> deleteAllPairs() {
        defaultService.deleteAllPairs();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = {"appointment/create/"})
    @ResponseBody
    public ResponseEntity<Void> createAppointment(@Valid @RequestBody Appointment appointment) {
        appointment.setId(null);
        defaultService.createAppointment(appointment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = {"appointment/{id}"})
    @ResponseBody
    public ResponseEntity<Appointment> getAppointment(@PathVariable Long id) {
        return new ResponseEntity<>(
                defaultService.getAppointmentById(id).orElseThrow(() ->
                        new EntityNotFoundException(Constants.ERROR_APPOINTMENT_NOT_FOUND)),
                HttpStatus.OK);
    }

    @GetMapping(path = {"appointment/all"})
    @ResponseBody
    public ResponseEntity<Iterable<Appointment>> getAllAppointments() {
        Iterable<Appointment> appointments = defaultService.getAllAppointments();
        if (!appointments.iterator().hasNext()) throw new EntityNotFoundException(Constants.ERROR_APPOINTMENT_NOT_FOUND);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PostMapping(path = {"appointment/update/"})
    @ResponseBody
    public ResponseEntity<Void> updateAppointment(@Valid @RequestBody Appointment appointment) {
        if (!defaultService.updateAppointment(appointment)) throw new EntityExistsException(Constants.ERROR_APPOINTMENT_NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"appointment/{id}"})
    @ResponseBody
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        if (!defaultService.deleteAppointmentById(id)) throw new EntityExistsException(Constants.ERROR_APPOINTMENT_NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"appointment/all"})
    @ResponseBody
    public ResponseEntity<Void> deleteAllAppointments() {
        defaultService.deleteAllAppointments();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
