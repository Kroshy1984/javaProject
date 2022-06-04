package ru.sfedu.FatCalculator.dataprovider;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import ru.sfedu.FatCalculator.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class DataProviderXmlTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(DataProviderXmlTest.class);

    DataProviderXml dataProviderXml = new DataProviderXml();

    @Test
    public void addUserPositive() throws IOException {
        log.debug("addUserTest success");
        User user = createUser(17, "timur", "tokaev");

        assertTrue(dataProviderXml.addUser(user));
        dataProviderXml.deleteUserById(user.getId());
    }

    @Test
    public void addUserNegative() {
        log.debug("addUser Test fail");
        User emptyUser = null;

        assertFalse(dataProviderXml.addUser(emptyUser));
    }

    @Test
    public void getUserByIdPositive() throws IOException {
        log.debug("getUserById success");
        User user = createUser(17, "timur", "tokaev");
        dataProviderXml.addUser(user);

        assertEquals(user.getName(), dataProviderXml.getUserById(user.getId()).get().getName());
//        dataProviderXml.deleteUserById(user.getId());
    }

    @Test
    public void getUserByIdNegative() throws IOException {
        log.debug("getUserById fail");
        User user = createUser(17, "timur", "tokaev");
        dataProviderXml.addUser(user);

        assertNotEquals(user, dataProviderXml.getUserById(100));
        dataProviderXml.deleteUserById(user.getId());
    }

    @Test
    public void updateUserPositive() throws IOException {
        log.debug("updateUser success");
        User User = createUser(17, "timur", "tokaev");
        User User2 = User;
        User2.setSurname("tokarev");
        dataProviderXml.addUser(User);
        dataProviderXml.addUser(User2);

        Assert.assertTrue(dataProviderXml.updateUser(User2, User.getId()));
        dataProviderXml.deleteUserById(User.getId());
        dataProviderXml.deleteUserById(User2.getId());
    }

    @Test
    public void updateUserNegative() throws IOException {
        log.debug("updateUser fail");
        User User = createUser(17, "timur", "tokaev");
        dataProviderXml.addUser(User);

        Assert.assertFalse(dataProviderXml.updateUser(User, 1));
        dataProviderXml.deleteUserById(User.getId());
    }

    @Test
    public void deleteUserByIdPositive() throws IOException {
        log.debug("deleteUser success");
        User user = createUser(17, "timur", "tokaev");
        dataProviderXml.addUser(user);

        assertTrue(dataProviderXml.deleteUserById(user.getId()));
    }

    @Test
    public void deleteUserByIdNegative() throws IOException {
        log.debug("deleteUser fail");
        User user = createUser(17, "timur", "tokaev");
        dataProviderXml.addUser(user);

        assertFalse(dataProviderXml.deleteUserById(100));
    }

    @Test
    public void addResultPositive() throws IOException {
        log.debug("addResultTest success");
        Result Result = createResult(17, 33, 11);

        assertTrue(dataProviderXml.addResult(Result));
        dataProviderXml.deleteResultById(Result.getResultid());
    }

    @Test
    public void addResultNegative() {
        log.debug("addResult Test fail");
        Result emptyResult = null;

        assertFalse(dataProviderXml.addResult(emptyResult));
    }

    @Test
    public void getResultByIdPositive() throws IOException {
        log.debug("getResultById success");
        Result Result = createResult(17, 33, 11);
        dataProviderXml.addResult(Result);

        Assert.assertEquals(dataProviderXml.getResultById(Result.getResultid()).get().getResultid(), Result.getResultid());
        dataProviderXml.deleteResultById(Result.getResultid());
    }

    @Test
    public void getResultByIdNegative() throws IOException {
        log.debug("getResultById fail");
        Result Result = createResult(17, 33, 11);
        dataProviderXml.addResult(Result);

        assertNotEquals(Result, dataProviderXml.getResultById(100));
        dataProviderXml.deleteResultById(Result.getResultid());
    }

    @Test
    public void updateResultPositive() throws IOException {
        log.debug("updateResult success");
        Result Result = createResult(17, 33, 11);
        Result Result2 = Result;
        Result2.setFatPercentage(22);
        dataProviderXml.addResult(Result);
        dataProviderXml.addResult(Result2);

        Assert.assertTrue(dataProviderXml.updateResult(Result2, Result.getResultid()));
        dataProviderXml.deleteResultById(Result.getResultid());
        dataProviderXml.deleteResultById(Result2.getResultid());
    }

    @Test
    public void updateResultNegative() throws IOException {
        log.debug("updateResult fail");
        Result Result = createResult(17, 33, 11);
        dataProviderXml.addResult(Result);

        Assert.assertFalse(dataProviderXml.updateResult(Result, 1));
        dataProviderXml.deleteResultById(Result.getResultid());
    }

    @Test
    public void deleteResultByIdPositive() throws IOException {
        log.debug("deleteResult success");
        Result Result = createResult(17, 33, 11);
        dataProviderXml.addResult(Result);

        assertTrue(dataProviderXml.deleteResultById(Result.getResultid()));
    }

    @Test
    public void deleteResultByIdNegative() throws IOException {
        log.debug("deleteResult fail");
        Result Result = createResult(17, 33, 11);
        dataProviderXml.addResult(Result);

        assertFalse(dataProviderXml.deleteResultById(100));
    }

    @Test
    public void addTapePositive() throws IOException {
        log.debug("addTapeTest success");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);

        assertTrue(dataProviderXml.addTape(Tape));
        dataProviderXml.deleteTapeById(Tape.getMeasurementId());
    }

    @Test
    public void addTapeNegative() {
        log.debug("addTape Test fail");
        Tape emptyTape = null;

        assertFalse(dataProviderXml.addTape(emptyTape));
    }

    @Test
    public void getTapeByIdPositive() throws IOException {
        log.debug("getTapeById success");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderXml.addTape(Tape);

        assertEquals(Tape.getAge(), dataProviderXml.getTapeById(Tape.getMeasurementId()).get().getAge());
        dataProviderXml.deleteTapeById(Tape.getMeasurementId());
    }

    @Test
    public void getTapeByIdNegative() throws IOException {
        log.debug("getTapeById fail");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderXml.addTape(Tape);

        assertNotEquals(Tape, dataProviderXml.getTapeById(100));
        dataProviderXml.deleteTapeById(Tape.getMeasurementId());
    }

    @Test
    public void updateTapePositive() throws IOException {
        log.debug("updateTape success");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        Tape Tape2 = Tape;
        Tape2.setAge(22);
        dataProviderXml.addTape(Tape);
        dataProviderXml.addTape(Tape2);

        Assert.assertTrue(dataProviderXml.updateTape(Tape2, Tape.getMeasurementId()));
        dataProviderXml.deleteTapeById(Tape.getMeasurementId());
        dataProviderXml.deleteTapeById(Tape2.getMeasurementId());
    }

    @Test
    public void updateTapeNegative() throws IOException {
        log.debug("updateTape fail");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderXml.addTape(Tape);

        Assert.assertFalse(dataProviderXml.updateTape(Tape, 1));
        dataProviderXml.deleteTapeById(Tape.getMeasurementId());
    }

    @Test
    public void deleteTapeByIdPositive() throws IOException {
        log.debug("deleteTape success");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderXml.addTape(Tape);

        assertTrue(dataProviderXml.deleteTapeById(Tape.getMeasurementId()));
    }

    @Test
    public void deleteTapeByIdNegative() throws IOException {
        log.debug("deleteTape fail");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderXml.addTape(Tape);

        assertFalse(dataProviderXml.deleteTapeById(100));
        dataProviderXml.deleteTapeById(Tape.getMeasurementId());
    }

    @Test
    public void addThreeSiteSkinFoldPositive() throws IOException {
        log.debug("addThreeSiteSkinFoldTest success");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22, 22, 22, 22, 22, 17, "male", 21, 165, 65);

        assertTrue(dataProviderXml.addThreeSiteSkinFold(ThreeSiteSkinFold));
        dataProviderXml.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
    }

    @Test
    public void addThreeSiteSkinFoldNegative() {
        log.debug("addThreeSiteSkinFold Test fail");
        ThreeSiteSkinFold emptyThreeSiteSkinFold = null;

        assertFalse(dataProviderXml.addThreeSiteSkinFold(emptyThreeSiteSkinFold));
    }

    @Test
    public void getThreeSiteSkinFoldByIdPositive() throws IOException {
        log.debug("getThreeSiteSkinFoldById success");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22, 22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderXml.addThreeSiteSkinFold(ThreeSiteSkinFold);

        assertEquals(ThreeSiteSkinFold.getAge(), dataProviderXml.getThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId()).get().getAge());
        dataProviderXml.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
    }

    @Test
    public void getThreeSiteSkinFoldByIdNegative() throws IOException {
        log.debug("getThreeSiteSkinFoldById fail");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22, 22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderXml.addThreeSiteSkinFold(ThreeSiteSkinFold);

        assertNotEquals(ThreeSiteSkinFold, dataProviderXml.getThreeSiteSkinFoldById(100));
        dataProviderXml.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
    }

    @Test
    public void updateThreeSiteSkinFoldPositive() throws IOException {
        log.debug("updateThreeSiteSkinFold success");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22, 22, 22, 22, 22, 17, "male", 21, 165, 65);
        ThreeSiteSkinFold ThreeSiteSkinFold2 = ThreeSiteSkinFold;
        ThreeSiteSkinFold2.setAge(22);
        dataProviderXml.addThreeSiteSkinFold(ThreeSiteSkinFold);
        dataProviderXml.addThreeSiteSkinFold(ThreeSiteSkinFold2);

        Assert.assertTrue(dataProviderXml.updateThreeSiteSkinFold(ThreeSiteSkinFold2, ThreeSiteSkinFold.getMeasurementId()));
        dataProviderXml.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
        dataProviderXml.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold2.getMeasurementId());
    }

    @Test
    public void updateThreeSiteSkinFoldNegative() throws IOException {
        log.debug("updateThreeSiteSkinFold fail");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22, 22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderXml.addThreeSiteSkinFold(ThreeSiteSkinFold);

        Assert.assertFalse(dataProviderXml.updateThreeSiteSkinFold(ThreeSiteSkinFold, 1));
        dataProviderXml.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
    }

    @Test
    public void deleteThreeSiteSkinFoldByIdPositive() throws IOException {
        log.debug("deleteThreeSiteSkinFold success");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22, 22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderXml.addThreeSiteSkinFold(ThreeSiteSkinFold);

        assertTrue(dataProviderXml.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId()));
    }

    @Test
    public void deleteThreeSiteSkinFoldByIdNegative() throws IOException {
        log.debug("deleteThreeSiteSkinFold fail");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22, 22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderXml.addThreeSiteSkinFold(ThreeSiteSkinFold);

        assertFalse(dataProviderXml.deleteThreeSiteSkinFoldById(100));
        dataProviderXml.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
    }

    @Test
    public void addFourSiteSkinFoldPositive() throws IOException {
        log.debug("addFourSiteSkinFoldTest success");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);

        assertTrue(dataProviderXml.addFourSiteSkinFold(FourSiteSkinFold));
        dataProviderXml.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
    }

    @Test
    public void addFourSiteSkinFoldNegative() {
        log.debug("addFourSiteSkinFold Test fail");
        FourSiteSkinFold emptyFourSiteSkinFold = null;

        assertFalse(dataProviderXml.addFourSiteSkinFold(emptyFourSiteSkinFold));
    }

    @Test
    public void getFourSiteSkinFoldByIdPositive() throws IOException {
        log.debug("getFourSiteSkinFoldById success");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderXml.addFourSiteSkinFold(FourSiteSkinFold);

        Assert.assertEquals(FourSiteSkinFold.getMeasurementId(), dataProviderXml.getFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId()).get().getMeasurementId());
        dataProviderXml.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
    }

    @Test
    public void getFourSiteSkinFoldByIdNegative() throws IOException {
        log.debug("getFourSiteSkinFoldById fail");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderXml.addFourSiteSkinFold(FourSiteSkinFold);

        assertNotEquals(FourSiteSkinFold, dataProviderXml.getFourSiteSkinFoldById(100));
        dataProviderXml.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
    }

    @Test
    public void updateFourSiteSkinFoldPositive() throws IOException {
        log.debug("updateFourSiteSkinFold success");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        FourSiteSkinFold FourSiteSkinFold2 = FourSiteSkinFold;
        FourSiteSkinFold2.setAge(22);
        dataProviderXml.addFourSiteSkinFold(FourSiteSkinFold);
        dataProviderXml.addFourSiteSkinFold(FourSiteSkinFold2);

        Assert.assertTrue(dataProviderXml.updateFourSiteSkinFold(FourSiteSkinFold2, FourSiteSkinFold.getMeasurementId()));
        dataProviderXml.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
        dataProviderXml.deleteFourSiteSkinFoldById(FourSiteSkinFold2.getMeasurementId());
    }

    @Test
    public void updateFourSiteSkinFoldNegative() throws IOException {
        log.debug("updateFourSiteSkinFold fail");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderXml.addFourSiteSkinFold(FourSiteSkinFold);

        Assert.assertFalse(dataProviderXml.updateFourSiteSkinFold(FourSiteSkinFold, 1));
        dataProviderXml.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
    }

    @Test
    public void deleteFourSiteSkinFoldByIdPositive() throws IOException {
        log.debug("deleteFourSiteSkinFold success");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderXml.addFourSiteSkinFold(FourSiteSkinFold);

        assertTrue(dataProviderXml.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId()));
    }

    @Test
    public void deleteFourSiteSkinFoldByIdNegative() throws IOException {
        log.debug("deleteFourSiteSkinFold fail");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderXml.addFourSiteSkinFold(FourSiteSkinFold);

        assertFalse(dataProviderXml.deleteFourSiteSkinFoldById(100));
        dataProviderXml.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
    }

//    @Test
//    public void measureFatPositive() throws Exception {
//        log.debug("measureFat success");
//        assertTrue(dataProviderXml.measureFat("Tape", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, true));
//        dataProviderXml.deleteAll();
//    }
//
//    @Test
//    public void measureFatNegative() throws Exception {
//        log.debug("measureFat failed");
//        assertFalse(dataProviderXml.measureFat("Tap", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, true));
//    }
//
//    @Test
//    public void analysisPositive() throws Exception {
//        dataProviderXml.measureFat("Tape", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, false);
//        dataProviderXml.measureFat("Tape", 28282, "timur", "tokaev", 63, 179, 20, "m", 62, 27, 0, 0, false);
//        log.debug("analysis success");
//        assertTrue(dataProviderXml.analysis(28282));
//        dataProviderXml.deleteAll();
//    }
//
//    @Test
//    public void analysisNegative() throws Exception {
//        dataProviderXml.measureFat("Tape", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, false);
//        dataProviderXml.measureFat("Tape", 28282, "timur", "tokaev", 63, 179, 20, "m", 62, 27, 0, 0, false);
//        log.debug("analysis failed");
//        assertFalse(dataProviderXml.analysis(28281));
//        dataProviderXml.deleteAll();
//    }
//
//    @Test
//    public void analysisExtendedPositive() throws Exception {
//        dataProviderXml.measureFat("Tape", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, false);
//        dataProviderXml.measureFat("Tape", 28282, "timur", "tokaev", 63, 179, 20, "m", 62, 27, 0, 0, false);
//        log.debug("analysisExtended success");
//        assertTrue(dataProviderXml.analysis(28282, "22.11.2019", "24.03.2021"));
//        dataProviderXml.deleteAll();
//    }
//
//    @Test
//    public void analysisExtendedNegative() throws Exception {
//        dataProviderXml.measureFat("Tape", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, false);
//        dataProviderXml.measureFat("Tape", 28282, "timur", "tokaev", 63, 179, 20, "m", 62, 27, 0, 0, false);
//        log.debug("analysisExtended failed");
//        assertFalse(dataProviderXml.analysis(28282, "22.11.2022", "24.05.2022"));
//        dataProviderXml.deleteAll();
//    }

}