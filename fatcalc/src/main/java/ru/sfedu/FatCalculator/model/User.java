package ru.sfedu.FatCalculator.model;

import com.opencsv.bean.CsvBindByName;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import ru.sfedu.FatCalculator.Constants;
import ru.sfedu.FatCalculator.utils.ConfigurationUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


public class User implements Serializable {
    @Attribute
    @CsvBindByName(column = "userid")
    private long id;
    @Element
    @CsvBindByName(column = "name")
    private String name;
    @Element
    @CsvBindByName(column = "surname")
    private String surname ;

    public User() {}

    public User(String name, String surname) throws IOException {
        this.id = System.currentTimeMillis();
        this.name = name;
        this.surname = surname;
    }

    public User(long id, String name, String surname){
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public long getId() {
        return id;
    }

    public void setId() {
        id = System.currentTimeMillis();
    }

    public void setId(long Id) {
        id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
