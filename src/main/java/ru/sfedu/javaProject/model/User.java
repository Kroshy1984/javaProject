package ru.sfedu.javaProject.model;

import java.util.Objects;

public class User {

    private Long id;
    private String firstname;
    private String surname;
    private String patronymic;
    private Long passportId;
    private Long drivingLicenseId;
    private Long diplomaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Long getPassportId() {
        return passportId;
    }

    public void setPassportId(Long passportId) {
        this.passportId = passportId;
    }

    public Long getDrivingLicenseId() {
        return drivingLicenseId;
    }

    public void setDrivingLicenseId(Long drivingLicenseId) {
        this.drivingLicenseId = drivingLicenseId;
    }

    public Long getDiplomaId() {
        return diplomaId;
    }

    public void setDiplomaId(Long diplomaId) {
        this.diplomaId = diplomaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstname, user.firstname) && Objects.equals(surname, user.surname) && Objects.equals(patronymic, user.patronymic) && Objects.equals(passportId, user.passportId) && Objects.equals(drivingLicenseId, user.drivingLicenseId) && Objects.equals(diplomaId, user.diplomaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, surname, patronymic, passportId, drivingLicenseId, diplomaId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", passportId=" + passportId +
                ", drivingLicenseId=" + drivingLicenseId +
                ", diplomaId=" + diplomaId +
                '}';
    }
}
