package ru.sfedu.FatCalculator.dataprovider;

import ru.sfedu.FatCalculator.model.*;

import java.io.IOException;
import java.util.Date;

public class BaseTest {

    public User createUser(long id, String name, String surname) throws IOException {
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

    public Tape createTape(double abdomen, double neck, double waist, double hips, long userId, String gender, int age, double height, double weight) throws IOException {
        Tape Tape = new Tape();
        Tape.setMeasurementId();
        Tape.setAge(age);
        Tape.setHeight(height);
        Tape.setWeight(weight);
        Tape.setGender(gender);
        Tape.setUserid(userId);
        Tape.setHips(hips);
        Tape.setWaist(waist);
        Tape.setNeck(neck);
        return Tape;
    }

    public ThreeSiteSkinFold createThreeSiteSkinFold(double abdominal, double chest, double suprailiac, double thigh, double tricep, long userId, String gender, int age, double height, double weight) throws IOException {
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
    
    public FourSiteSkinFold createFourSiteSkinFold(double abdominal, double suprailiac, double thigh, double tricep, long userId, String gender, int age, double height, double weight) throws IOException {
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
    
    public Result createResult(long resultId, double fatPercentage, int id) throws IOException {
        Result result = new Result();
        result.setUserid(id);
        result.setDate();
        result.setFatPercentage(fatPercentage);
        result.setResultid(resultId);
        result.setStage(Stage.OBESE);
        return result;
    }

    public Result createResult(long resultId, double fatPercentage, int id, Stage stage) throws IOException {
        Result result = new Result();
        result.setUserid(id);
        result.setDate();
        result.setFatPercentage(fatPercentage);
        result.setResultid(resultId);
        result.setStage(stage);
        return result;
    }

    public Result createResult(long id, double fatPercentage, String d) throws IOException {
        Result result = new Result();
        result.setUserid(id);
        result.setDate(d);
        result.setFatPercentage(fatPercentage);
        result.setResultid();
        result.setStage(Stage.OBESE);
        return result;
    }

}
