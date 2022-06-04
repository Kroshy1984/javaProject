package ru.sfedu.FatCalculator.dataprovider;

import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.FatCalculator.model.*;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class DataProviderH2Test extends BaseTest {

    private static final Logger log = LogManager.getLogger(DataProviderH2Test.class);

    DataProviderH2 dataProviderH2 = new DataProviderH2();

    @Before
    public void setUp(){
        dataProviderH2.dropTables();
        dataProviderH2.createTables();
    }

    @After
    public void setUpAfter(){
        dataProviderH2.dropTables();
        dataProviderH2.createTables();
    }

    @Test
    public void addUserPositive() throws IOException {
        log.debug("addUserTest success");
        User User = createUser(17, "timur", "tokaev");

        assertTrue(dataProviderH2.addUser(User));
        dataProviderH2.deleteUserById(User.getId());
    }

    @Test
    public void addUserNegative() {
        log.debug("addUser Test fail");
        User emptyUser = null;

        assertFalse(dataProviderH2.addUser(emptyUser));
    }

    @Test
    public void getUserByIdPositive() throws IOException {
        log.debug("getUserById success");
        User User = createUser(17, "timur", "tokaev");
        dataProviderH2.addUser(User);

        assertEquals(User.getName(), dataProviderH2.getUserById(User.getId()).get().getName());
        dataProviderH2.deleteUserById(User.getId());
    }

    @Test
    public void getUserByIdNegative() throws IOException {
        log.debug("getUserById fail");
        User user = createUser(17, "timur", "tokaev");
        dataProviderH2.addUser(user);

        assertNotEquals(user, dataProviderH2.getUserById(100));
        dataProviderH2.deleteUserById(user.getId());
    }

    @Test
    public void updateUserPositive() throws IOException {
        log.debug("updateUser success");
        User User = createUser(17, "timur", "tokaev");
        User User2 = User;
        User2.setSurname("tokarev");
        dataProviderH2.addUser(User);
        dataProviderH2.addUser(User2);

        Assert.assertTrue(dataProviderH2.updateUser(User2, User.getId()));
        dataProviderH2.deleteUserById(User.getId());
        dataProviderH2.deleteUserById(User2.getId());
    }

    @Test
    public void updateUserNegative() throws IOException {
        log.debug("updateUser fail");
        User User = createUser(17, "timur", "tokaev");
        dataProviderH2.addUser(User);

        Assert.assertFalse(dataProviderH2.updateUser(User, 1));
        dataProviderH2.deleteUserById(User.getId());
    }

    @Test
    public void deleteUserByIdPositive() throws IOException {
        log.debug("deleteUser success");
        User user = createUser(17, "timur", "tokaev");
        dataProviderH2.addUser(user);

        assertTrue(dataProviderH2.deleteUserById(user.getId()));
        dataProviderH2.deleteUserById(user.getId());
    }

    @Test
    public void deleteUserByIdNegative() throws IOException {
        log.debug("deleteUser fail");
        User User = createUser(17, "timur", "tokaev");
        dataProviderH2.addUser(User);

        assertFalse(dataProviderH2.deleteUserById(100));
        dataProviderH2.deleteUserById(User.getId());
    }

    @Test
    public void addResultPositive() throws IOException {
        log.debug("addResultTest success");
        Result Result = createResult( 33, 22, "22.11.2021");

        assertTrue(dataProviderH2.addResult(Result));
        dataProviderH2.deleteResultById(Result.getResultid());
    }

    @Test
    public void addResultNegative() {
        log.debug("addResult Test fail");
        Result emptyResult = null;

        assertFalse(dataProviderH2.addResult(emptyResult));
    }

    @Test
    public void getResultByIdPositive() throws IOException {
        log.debug("getResultById success");
        Result Result = createResult(17, 33, 11);
        dataProviderH2.addResult(Result);

        Assert.assertEquals(dataProviderH2.getResultById(Result.getResultid()).get().getResultid(), Result.getResultid());
        dataProviderH2.deleteResultById(Result.getResultid());
    }

    @Test
    public void getResultByIdNegative() throws IOException {
        log.debug("getResultById fail");
        Result Result = createResult(17, 33, 11);
        dataProviderH2.addResult(Result);

        assertNotEquals(Result, dataProviderH2.getResultById(100));
        dataProviderH2.deleteResultById(Result.getResultid());
    }

    @Test
    public void updateResultPositive() throws IOException {
        log.debug("updateResult success");
        Result Result = createResult(17, 33, 11);
        Result Result2 = Result;
        Result2.setFatPercentage(22);
        dataProviderH2.addResult(Result);
        dataProviderH2.addResult(Result2);

        Assert.assertTrue(dataProviderH2.updateResult(Result2, Result.getResultid()));
        dataProviderH2.deleteResultById(Result.getResultid());
        dataProviderH2.deleteResultById(Result2.getResultid());
    }

    @Test
    public void updateResultNegative() throws IOException {
        log.debug("updateResult fail");
        Result Result = createResult(17, 33, 11);
        dataProviderH2.addResult(Result);

        Assert.assertFalse(dataProviderH2.updateResult(Result, 1));
        dataProviderH2.deleteResultById(Result.getResultid());
    }

    @Test
    public void deleteResultByIdPositive() throws IOException {
        log.debug("deleteResult success");
        Result Result = createResult(17, 33, 11);
        dataProviderH2.addResult(Result);

        assertTrue(dataProviderH2.deleteResultById(Result.getResultid()));
    }

    @Test
    public void deleteResultByIdNegative() throws IOException {
        log.debug("deleteResult fail");
        Result Result = createResult(17, 33, 11);
        dataProviderH2.addResult(Result);

        assertFalse(dataProviderH2.deleteResultById(100));
        dataProviderH2.deleteResultById(Result.getResultid());
    }

    @Test
    public void addTapePositive() throws IOException {
        log.debug("addTapeTest success");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);

        assertTrue(dataProviderH2.addTape(Tape));
        dataProviderH2.deleteTapeById(Tape.getMeasurementId());
    }

    @Test
    public void addTapeNegative() {
        log.debug("addTape Test fail");
        Tape emptyTape = null;

        assertFalse(dataProviderH2.addTape(emptyTape));
    }

    @Test
    public void getTapeByIdPositive() throws IOException {
        log.debug("getTapeById success");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderH2.addTape(Tape);

        assertEquals(Tape.getAge(), dataProviderH2.getTapeById(Tape.getMeasurementId()).get().getAge());
        dataProviderH2.deleteTapeById(Tape.getMeasurementId());
    }

    @Test
    public void getTapeByIdNegative() throws IOException {
        log.debug("getTapeById fail");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderH2.addTape(Tape);

        assertNotEquals(Tape, dataProviderH2.getTapeById(100));
        dataProviderH2.deleteTapeById(Tape.getMeasurementId());
    }

    @Test
    public void updateTapePositive() throws IOException {
        log.debug("updateTape success");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        Tape Tape2 = Tape;
        Tape2.setAge(22);
        dataProviderH2.addTape(Tape);
        dataProviderH2.addTape(Tape2);

        Assert.assertTrue(dataProviderH2.updateTape(Tape2, Tape.getMeasurementId()));
        dataProviderH2.deleteTapeById(Tape.getMeasurementId());
        dataProviderH2.deleteTapeById(Tape2.getMeasurementId());
    }

    @Test
    public void updateTapeNegative() throws IOException {
        log.debug("updateTape fail");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderH2.addTape(Tape);

        Assert.assertFalse(dataProviderH2.updateTape(Tape, 1));
        dataProviderH2.deleteTapeById(Tape.getMeasurementId());
    }

    @Test
    public void deleteTapeByIdPositive() throws IOException {
        log.debug("deleteTape success");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderH2.addTape(Tape);

        assertTrue(dataProviderH2.deleteTapeById(Tape.getMeasurementId()));
    }

    @Test
    public void deleteTapeByIdNegative() throws IOException {
        log.debug("deleteTape fail");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderH2.addTape(Tape);

        assertFalse(dataProviderH2.deleteTapeById(100));
    }

    @Test
    public void addThreeSiteSkinFoldPositive() throws IOException {
        log.debug("addThreeSiteSkinFoldTest success");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22, 22, 22, 22, 22, 17, "male", 21, 165, 65);

        assertTrue(dataProviderH2.addThreeSiteSkinFold(ThreeSiteSkinFold));
        dataProviderH2.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
    }

    @Test
    public void addThreeSiteSkinFoldNegative() {
        log.debug("addThreeSiteSkinFold Test fail");
        ThreeSiteSkinFold emptyThreeSiteSkinFold = null;

        assertFalse(dataProviderH2.addThreeSiteSkinFold(emptyThreeSiteSkinFold));
    }

    @Test
    public void getThreeSiteSkinFoldByIdPositive() throws IOException {
        log.debug("getThreeSiteSkinFoldById success");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22, 22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderH2.addThreeSiteSkinFold(ThreeSiteSkinFold);

        assertEquals(ThreeSiteSkinFold.getAge(), dataProviderH2.getThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId()).get().getAge());
        dataProviderH2.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
    }

    @Test
    public void getThreeSiteSkinFoldByIdNegative() throws IOException {
        log.debug("getThreeSiteSkinFoldById fail");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22, 22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderH2.addThreeSiteSkinFold(ThreeSiteSkinFold);

        assertNotEquals(ThreeSiteSkinFold, dataProviderH2.getThreeSiteSkinFoldById(100));
        dataProviderH2.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
    }

    @Test
    public void updateThreeSiteSkinFoldPositive() throws IOException {
        log.debug("updateThreeSiteSkinFold success");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22, 22, 22, 22, 22, 17, "male", 21, 165, 65);
        ThreeSiteSkinFold ThreeSiteSkinFold2 = ThreeSiteSkinFold;
        ThreeSiteSkinFold2.setAge(22);
        dataProviderH2.addThreeSiteSkinFold(ThreeSiteSkinFold);
        dataProviderH2.addThreeSiteSkinFold(ThreeSiteSkinFold2);

        Assert.assertTrue(dataProviderH2.updateThreeSiteSkinFold(ThreeSiteSkinFold2, ThreeSiteSkinFold.getMeasurementId()));
        dataProviderH2.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
        dataProviderH2.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold2.getMeasurementId());
    }

    @Test
    public void updateThreeSiteSkinFoldNegative() throws IOException {
        log.debug("updateThreeSiteSkinFold fail");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22, 22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderH2.addThreeSiteSkinFold(ThreeSiteSkinFold);

        Assert.assertFalse(dataProviderH2.updateThreeSiteSkinFold(ThreeSiteSkinFold, 1));
        dataProviderH2.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
    }

    @Test
    public void deleteThreeSiteSkinFoldByIdPositive() throws IOException {
        log.debug("deleteThreeSiteSkinFold success");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22, 22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderH2.addThreeSiteSkinFold(ThreeSiteSkinFold);

        assertTrue(dataProviderH2.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId()));
    }

    @Test
    public void deleteThreeSiteSkinFoldByIdNegative() throws IOException {
        log.debug("deleteThreeSiteSkinFold fail");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22, 22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderH2.addThreeSiteSkinFold(ThreeSiteSkinFold);

        assertFalse(dataProviderH2.deleteThreeSiteSkinFoldById(100));
    }

    @Test
    public void addFourSiteSkinFoldPositive() throws IOException {
        log.debug("addFourSiteSkinFoldTest success");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);

        assertTrue(dataProviderH2.addFourSiteSkinFold(FourSiteSkinFold));
        dataProviderH2.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
    }

    @Test
    public void addFourSiteSkinFoldNegative() {
        log.debug("addFourSiteSkinFold Test fail");
        FourSiteSkinFold emptyFourSiteSkinFold = null;

        assertFalse(dataProviderH2.addFourSiteSkinFold(emptyFourSiteSkinFold));
    }

    @Test
    public void getFourSiteSkinFoldByIdPositive() throws IOException {
        log.debug("getFourSiteSkinFoldById success");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderH2.addFourSiteSkinFold(FourSiteSkinFold);

        Assert.assertEquals(FourSiteSkinFold.getMeasurementId(), dataProviderH2.getFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId()).get().getMeasurementId());
        dataProviderH2.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
    }

    @Test
    public void getFourSiteSkinFoldByIdNegative() throws IOException {
        log.debug("getFourSiteSkinFoldById fail");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderH2.addFourSiteSkinFold(FourSiteSkinFold);

        assertNotEquals(FourSiteSkinFold, dataProviderH2.getFourSiteSkinFoldById(100));
        dataProviderH2.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
    }

    @Test
    public void updateFourSiteSkinFoldPositive() throws IOException {
        log.debug("updateFourSiteSkinFold success");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        FourSiteSkinFold FourSiteSkinFold2 = FourSiteSkinFold;
        FourSiteSkinFold2.setAge(22);
        dataProviderH2.addFourSiteSkinFold(FourSiteSkinFold);
        dataProviderH2.addFourSiteSkinFold(FourSiteSkinFold2);

        Assert.assertTrue(dataProviderH2.updateFourSiteSkinFold(FourSiteSkinFold2, FourSiteSkinFold.getMeasurementId()));
        dataProviderH2.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
        dataProviderH2.deleteFourSiteSkinFoldById(FourSiteSkinFold2.getMeasurementId());
    }

    @Test
    public void updateFourSiteSkinFoldNegative() throws IOException {
        log.debug("updateFourSiteSkinFold fail");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderH2.addFourSiteSkinFold(FourSiteSkinFold);

        Assert.assertFalse(dataProviderH2.updateFourSiteSkinFold(FourSiteSkinFold, 1));
        dataProviderH2.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
    }

    @Test
    public void deleteFourSiteSkinFoldByIdPositive() throws IOException {
        log.debug("deleteFourSiteSkinFold success");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderH2.addFourSiteSkinFold(FourSiteSkinFold);

        assertTrue(dataProviderH2.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId()));
    }

    @Test
    public void deleteFourSiteSkinFoldByIdNegative() throws IOException {
        log.debug("deleteFourSiteSkinFold fail");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderH2.addFourSiteSkinFold(FourSiteSkinFold);

        assertFalse(dataProviderH2.deleteFourSiteSkinFoldById(100));
    }

    @Test
    public void getAllResultByIdPositive() throws IOException {
        log.debug("getResultById success");
        Result Result = createResult(17,33,33);
        dataProviderH2.addResult(Result);

        Assert.assertEquals(dataProviderH2.getAllResultById(Result.getUserid()), dataProviderH2.getAllResultById(Result.getUserid()));
    }

    @Test
    public void measureFatPositive() throws Exception {
        log.debug("measureFat success");
        assertTrue(dataProviderH2.measureFat("Tape", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, true));
    }

    @Test
    public void measureFatNegative() throws Exception {
        log.debug("measureFat failed");
        assertFalse(dataProviderH2.measureFat("Tap", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, true));
    }

    @Test
    public void analysisPositive() throws Exception {
        dataProviderH2.measureFat("Tape", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, false);
        dataProviderH2.measureFat("Tape", 28282, "timur", "tokaev", 63, 179, 20, "m", 62, 27, 0, 0, false);
        log.debug("analysis success");
        assertTrue(dataProviderH2.analysis(28282));
    }

    @Test
    public void analysisNegative() throws Exception {
        dataProviderH2.measureFat("Tape", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, false);
        dataProviderH2.measureFat("Tape", 28282, "timur", "tokaev", 63, 179, 20, "m", 62, 27, 0, 0, false);
        log.debug("analysis failed");
        assertFalse(dataProviderH2.analysis(28281));
    }

    @Test
    public void analysisExtendedPositive() throws Exception {
        dataProviderH2.measureFat("Tape", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, false);
        dataProviderH2.measureFat("Tape", 28282, "timur", "tokaev", 63, 179, 20, "m", 62, 27, 0, 0, false);
        log.debug("analysisExtended success");
        assertTrue(dataProviderH2.analysis(28282, "22.11.2019", "24.03.2021"));
    }

    @Test
    public void analysisExtendedNegative() throws Exception {
        dataProviderH2.measureFat("Tape", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, false);
        dataProviderH2.measureFat("Tape", 28282, "timur", "tokaev", 63, 179, 20, "m", 62, 27, 0, 0, false);
        log.debug("analysisExtended failed");
        assertFalse(dataProviderH2.analysis(28282, "22.11.2022", "24.05.2022"));
    }


}