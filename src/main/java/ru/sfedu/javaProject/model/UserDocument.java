package ru.sfedu.javaProject.model;

import java.util.Objects;

public class UserDocument {

    public enum UserDocumentType {
        DIPLOMA, PASSPORT, DRIVING_LICENSE
    }

    private long id;
    private UserDocumentType type;
    private String serialNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDocumentType getType() {
        return type;
    }

    public void setType(UserDocumentType type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDocument that = (UserDocument) o;
        return id == that.id && type == that.type && Objects.equals(serialNumber, that.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, serialNumber);
    }

    @Override
    public String toString() {
        return "UserDocument{" +
                "id=" + id +
                ", type=" + type +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
