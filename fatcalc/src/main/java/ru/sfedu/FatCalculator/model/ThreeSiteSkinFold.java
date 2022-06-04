package ru.sfedu.FatCalculator.model;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;
import java.util.Objects;

import static java.lang.Math.log;

public class ThreeSiteSkinFold extends Measurement implements Serializable {
    @CsvBindByName
    private double chest;
    @CsvBindByName
    private double thigh;
    @CsvBindByName
    private double tricep;
    @CsvBindByName
    private double suprailiac;
    @CsvBindByName
    private double abdominal;

    public ThreeSiteSkinFold() {}

    public ThreeSiteSkinFold(double thigh, double tricep, double suprailiac, int f) {
        this.thigh = thigh;
        this.tricep = tricep;
        this.suprailiac = suprailiac;
    }

    public ThreeSiteSkinFold(double thigh, double chest, double abdominal) {
        this.chest = chest;
        this.thigh = thigh;
        this.abdominal = abdominal;
    }

    public ThreeSiteSkinFold(long userid, double weight, double height, int age, String gender, double chest, double thigh, double tricep, double suprailiac, double abdominal) {
        super(userid, weight, height, age, gender);
        this.chest = chest;
        this.thigh = thigh;
        this.tricep = tricep;
        this.suprailiac = suprailiac;
        this.abdominal = abdominal;
    }

    public double getFatPercentage() {
        double fatPercentage, bodyDensity;
        if (Objects.equals(getGender(), "m")) {
            bodyDensity = 1.10938 - (0.0008276 * abdominal + thigh + chest) + (0.0000016 * (abdominal + thigh + chest * abdominal + thigh + chest)) - (0.0002574 * getAge());
        } else {
            bodyDensity = 1.0994921 - (0.0009929 * suprailiac + thigh + tricep) + (0.0000023 * (suprailiac + thigh + tricep * suprailiac + thigh + tricep)) - (0.0001392 * getAge());
        }
        fatPercentage = (495 / bodyDensity) - 450;
        return fatPercentage;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
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

    public double getAbdominal() {
        return abdominal;
    }

    public void setAbdominal(double abdominal) {
        this.abdominal = abdominal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ThreeSiteSkinFold that = (ThreeSiteSkinFold) o;
        return chest == that.chest && thigh == that.thigh && tricep == that.tricep && suprailiac == that.suprailiac && abdominal == that.abdominal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), chest, thigh, tricep, suprailiac, abdominal);
    }

    @Override
    public String toString() {
        return "ThreeSiteSkinfold{" +
                "chest=" + chest +
                ", thigh=" + thigh +
                ", tricep=" + tricep +
                ", suprailiac=" + suprailiac +
                ", abdominal=" + abdominal +
                '}';
    }
}
