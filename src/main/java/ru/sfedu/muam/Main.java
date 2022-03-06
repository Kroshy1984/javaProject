package ru.sfedu.muam;

import ru.sfedu.muam.api.DataProvider;
import ru.sfedu.muam.api.DataProviderJdbc;
import ru.sfedu.muam.model.Student;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Student student = new Student();
        student.setId();
        student.setName("danii");
        DataProvider dataProvider = new DataProviderJdbc();
        dataProvider.addStudentRecord(student);
        Student receivedStudent = dataProvider.getStudentById(student.getId()).get();
        System.out.println(receivedStudent);
    }
}
