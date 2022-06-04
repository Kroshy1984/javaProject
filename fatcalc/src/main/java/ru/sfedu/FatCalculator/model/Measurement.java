package ru.sfedu.FatCalculator.model;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;
import java.util.Objects;

public class Measurement implements Serializable {
    @CsvBindByName
    private long userid;
    @CsvBindByName
    private long measurementId;
    @CsvBindByName
    private double weight;
    @CsvBindByName
    private double height;
    @CsvBindByName
    private int age;
    @CsvBindByName
    private String gender;

    public Measurement() {}

    public Measurement(String g){
        this.gender = g;
    }

    public Measurement(long userid, double weight, double height, int age, String gender) {
        this.userid = userid;
        this.measurementId = System.currentTimeMillis();
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
    }

    public void setCommon(long userid, double weight, double height, int age, String gender){
        this.userid = userid;
        this.measurementId = System.currentTimeMillis();
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
    }


    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(long measurementId) {
        this.measurementId = measurementId;
    }

    public void setMeasurementId() {
        this.measurementId = System.currentTimeMillis();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Measurement)) return false;
        Measurement that = (Measurement) o;
        return userid == that.userid && measurementId == that.measurementId && Double.compare(that.weight, weight) == 0 && Double.compare(that.height, height) == 0 && age == that.age && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, measurementId, weight, height, age, gender);
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "userid=" + userid +
                ", measurementId=" + measurementId +
                ", weight=" + weight +
                ", height=" + height +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
