package ru.sfedu.FatCalculator.model;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;
import java.util.Objects;

public class FourSiteSkinFold extends Measurement implements Serializable {
    @CsvBindByName
    private double abdominal;
    @CsvBindByName
    private double thigh;
    @CsvBindByName
    private double tricep;
    @CsvBindByName
    private double suprailiac;

    public FourSiteSkinFold() {}

    public FourSiteSkinFold(double abdominal, double thigh, double tricep, double suprailiac) {
        this.abdominal = abdominal;
        this.thigh = thigh;
        this.tricep = tricep;
        this.suprailiac = suprailiac;
    }

    public FourSiteSkinFold(long userid, double weight, double height, int age, String gender, double abdominal, double thigh, double tricep, double suprailiac) {
        super(userid, weight, height, age, gender);
        this.abdominal = abdominal;
        this.thigh = thigh;
        this.tricep = tricep;
        this.suprailiac = suprailiac;
    }

    public double getFatPercentage() {
        double fatPercentage, bodyDensity;
        if (Objects.equals(getGender(), "m")) {
            bodyDensity = (0.29288 * abdominal+thigh+tricep+suprailiac) - (0.0005 * (abdominal+thigh+tricep+suprailiac *
                    abdominal+thigh+tricep+suprailiac)) + (0.15845 * getAge()) - 5.76377;
        } else {
            bodyDensity = (0.29669 * abdominal+thigh+tricep+suprailiac) - (0.00043 * (abdominal+thigh+tricep+suprailiac *
                    abdominal+thigh+tricep+suprailiac)) + (0.2963 * getAge()) + 1.4072;
        }
        fatPercentage = (495 / bodyDensity) - 450;
        return fatPercentage;
    }


    public double getAbdominal() {
        return abdominal;
    }

    public void setAbdominal(double abdominal) {
        this.abdominal = abdominal;
    }

    public double getThigh() {
        return thigh;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public double getTricep() {
        return tricep;
    }

    public void setTricep(double tricep) {
        this.tricep = tricep;
    }

    public double getSuprailiac() {
        return suprailiac;
    }

    public void setSuprailiac(double suprailiac) {
        this.suprailiac = suprailiac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FourSiteSkinFold that = (FourSiteSkinFold) o;
        return abdominal == that.abdominal && thigh == that.thigh && tricep == that.tricep && suprailiac == that.suprailiac;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), abdominal, thigh, tricep, suprailiac);
    }

    @Override
    public String toString() {
        return "ThreeSiteSkinfold{" +
                "abdominal=" + abdominal +
                ", thigh=" + thigh +
                ", tricep=" + tricep +
                ", suprailiac=" + suprailiac +
                '}';
    }
}
