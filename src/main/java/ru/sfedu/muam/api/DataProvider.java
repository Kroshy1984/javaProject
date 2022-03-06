package ru.sfedu.muam.api;

import ru.sfedu.muam.model.Student;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DataProvider {
    Optional<List<Student>> getAllStudent();
    boolean addStudentRecord(Student student);
    boolean deleteStudentRecord(String id);
    boolean updateStudentRecord(Student student, String id);
    Optional<Student> getStudentById(String id) throws IOException;
}
