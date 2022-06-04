package ru.sfedu.FatCalculator.model;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;
import java.util.Objects;

import static java.lang.Math.log;

public class Tape extends Measurement implements Serializable {
    @CsvBindByName
    private double abdomen;
    @CsvBindByName
    private double neck;
    @CsvBindByName
    private double waist;
    @CsvBindByName
    private double hips;

    public Tape() {}

    public Tape(double abd, double neck) {
        this.abdomen = abd;
        this.neck = neck;
    }

    public Tape(double neck, double waist, double hips) {
        this.neck = neck;
        this.waist = waist;
        this.hips = hips;
    }

    public Tape(long userid, double weight, double height, int age, String gender, double abdomen, double neck, double waist, double hips) {
        super(userid, weight, height, age, gender);
        this.abdomen = abdomen;
        this.neck = neck;
        this.waist = waist;
        this.hips = hips;
    }

    public double getFatPercentage(){
        double fatPercentage;
        if (Objects.equals(getGender(), "m")){
            fatPercentage = 86.01 * log(abdomen - neck) - 70.041 * log(getHeight()) + 36.76;
        }
        else{
            fatPercentage = 163.205 * log(waist + hips - neck) - 97.684 * log(getHeight()) - 78.387;
        }
        return fatPercentage;
    }

    public double getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(double abdomen) {
        this.abdomen = abdomen;
    }

    public double getNeck() {
        return neck;
    }

    public void setNeck(double neck) {
        this.neck = neck;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getHips() {
        return hips;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tape tape = (Tape) o;
        return abdomen == tape.abdomen && neck == tape.neck && waist == tape.waist && hips == tape.hips;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), abdomen, neck, waist, hips);
    }

    @Override
    public String toString() {
        return "Tape{" +
                "abdomen=" + abdomen +
                ", neck=" + neck +
                ", waist=" + waist +
                ", hips=" + hips +
                '}';
    }
}
