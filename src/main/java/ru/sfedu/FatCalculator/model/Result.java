package ru.sfedu.FatCalculator.model;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Result implements Serializable {
    @Attribute
    @CsvBindByName
    private long resultid;
    @Element
    @CsvBindByName(column="Date", required=true)
    private String date;
    @Element
    @CsvBindByName
    private double fatPercentage;
    @Element
    @CsvBindByName
    private long userid;
    @Element
    @CsvBindByName
    private Stage stage;


    public Result() {}

    public Result(long userid, double fatPercentage, Stage stage) {
        this.resultid = System.currentTimeMillis();
        DateFormat date = new SimpleDateFormat("dd.MM.yyyy");
        this.date = date.format(new Date());
        this.fatPercentage = fatPercentage;
        this.userid = userid;
        this.stage = stage;
    }

    public Result(long userid, double fatPercentage, Stage stage, String date) {
        this.resultid = System.currentTimeMillis();
        DateFormat d = new SimpleDateFormat("dd.MM.yyyy");
        this.date = d.format(date);
        this.fatPercentage = fatPercentage;
        this.userid = userid;
        this.stage = stage;
    }


    public String getDate() { return date; }

    public void setDate(String d) {
        this.date = d;
    }

    public void setDate() {
        DateFormat date = new SimpleDateFormat("dd.MM.yyyy");
        this.date = date.format(new Date());
    }

    public double getFatPercentage() {
        return fatPercentage;
    }

    public void setFatPercentage(double fatPercentage) {
        this.fatPercentage = fatPercentage;
    }

    public long getResultid() {
        return resultid;
    }

    public void setResultid(long resultid) {
        this.resultid = resultid;
    }

    public void setResultid() { this.resultid = System.currentTimeMillis();}

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setStage(String gen) {
        if (gen.equals("m")) {
            if (fatPercentage < 8)
                setStage(Stage.UNDERWEIGHT);
            else if (fatPercentage >= 8 && fatPercentage < 19)
                setStage(Stage.HEAlTHY);
            else if(fatPercentage >= 19 && fatPercentage < 25)
                setStage(Stage.OVERWEIGHT);
            else if(fatPercentage >= 25 && fatPercentage < 30)
                setStage(Stage.OBESE);
            else
                setStage(Stage.EXTREMELY_OBESE);
        }
        else{
            if (fatPercentage < 21)
                setStage(Stage.UNDERWEIGHT);
            else if (fatPercentage >= 21 && fatPercentage < 33)
                setStage(Stage.HEAlTHY);
            else if(fatPercentage >= 33 && fatPercentage < 39)
                setStage(Stage.OVERWEIGHT);
            else if(fatPercentage >= 39 && fatPercentage < 44)
                setStage(Stage.OBESE);
            else
                setStage(Stage.EXTREMELY_OBESE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Double.compare(result.fatPercentage, fatPercentage) == 0 && resultid == result.resultid && userid == result.userid && stage == result.stage && Objects.equals(date, result.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, fatPercentage, resultid, userid, stage);
    }

    @Override
    public String toString() {
        return "Result{" +
                "date='" + date + '\'' +
                ", fatPercentage=" + fatPercentage +
                ", resultid=" + resultid +
                ", userid=" + userid +
                ", stage=" + stage +
                '}';
    }
}
