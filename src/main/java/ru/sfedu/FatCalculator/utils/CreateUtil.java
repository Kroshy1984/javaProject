package ru.sfedu.FatCalculator.utils;

import ru.sfedu.FatCalculator.model.*;

import java.io.IOException;

public class CreateUtil {

    public static User createUser(long id, String name, String surname) throws IOException {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        return user;
    }

    public User createUser(String name, String surname) throws IOException {
        User user = new User();
        user.setId();
        user.setName(name);
        user.setSurname(surname);
        return user;
    }

    public static Result createResult(long userid, double fatPercentage) throws IOException {
        Result result = new Result();
        result.setUserid(userid);
        result.setDate();
        result.setFatPercentage(fatPercentage);
        result.setResultid();
        result.setStage(Stage.OBESE);
        return result;
    }

    public Result createResult(long userid, double fatPercentage, Stage stage) throws IOException {
        Result result = new Result();
        result.setUserid(userid);
        result.setDate();
        result.setFatPercentage(fatPercentage);
        result.setResultid();
        result.setStage(stage);
        return result;
    }

    public Result createResult(long userid, double fatPercentage, String date) throws IOException {
        Result result = new Result();
        result.setUserid(userid);
        result.setDate(date);
        result.setFatPercentage(fatPercentage);
        result.setResultid();
        result.setStage(Stage.OBESE);
        return result;
    }

    public static Result createResult(long userid, double fatPercentage, String date, Stage stage) throws IOException {
        Result result = new Result();
        result.setUserid(userid);
        result.setDate(date);
        result.setFatPercentage(fatPercentage);
        result.setResultid();
        result.setStage(stage);
        return result;
    }

    public static Tape createTape(long userId, String gender, int age, double height, double weight, double abdomen, double neck, double waist, double hips) throws IOException {
        Tape Tape = new Tape();
        Tape.setMeasurementId();
        Tape.setAge(age);
        Tape.setHeight(height);
        Tape.setWeight(weight);
        Tape.setGender(gender);
        Tape.setUserid(userId);
        Tape.setAbdomen(abdomen);
        Tape.setHips(hips);
        Tape.setWaist(waist);
        Tape.setNeck(neck);
        return Tape;
    }

    public static ThreeSiteSkinFold createThreeSiteSkinFold(long userId, String gender, int age, double height, double weight, double abdominal, double chest, double suprailiac, double thigh, double tricep) throws IOException {
        ThreeSiteSkinFold ThreeSiteSkinfold = new ThreeSiteSkinFold();
        ThreeSiteSkinfold.setMeasurementId();
        ThreeSiteSkinfold.setAge(age);
        ThreeSiteSkinfold.setHeight(height);
        ThreeSiteSkinfold.setWeight(weight);
        ThreeSiteSkinfold.setGender(gender);
        ThreeSiteSkinfold.setUserid(userId);
        ThreeSiteSkinfold.setAbdominal(abdominal);
        ThreeSiteSkinfold.setChest(chest);
        ThreeSiteSkinfold.setSuprailiac(suprailiac);
        ThreeSiteSkinfold.setThigh(thigh);
        ThreeSiteSkinfold.setTricep(tricep);
        return ThreeSiteSkinfold;
    }

    public static FourSiteSkinFold createFourSiteSkinFold(long userId, String gender, int age, double height, double weight, double abdominal, double suprailiac, double thigh, double tricep) throws IOException {
        FourSiteSkinFold FourSiteSkinfold = new FourSiteSkinFold();
        FourSiteSkinfold.setMeasurementId();
        FourSiteSkinfold.setAge(age);
        FourSiteSkinfold.setHeight(height);
        FourSiteSkinfold.setWeight(weight);
        FourSiteSkinfold.setGender(gender);
        FourSiteSkinfold.setUserid(userId);
        FourSiteSkinfold.setAbdominal(abdominal);
        FourSiteSkinfold.setSuprailiac(suprailiac);
        FourSiteSkinfold.setThigh(thigh);
        FourSiteSkinfold.setTricep(tricep);
        return FourSiteSkinfold;
    }
}
