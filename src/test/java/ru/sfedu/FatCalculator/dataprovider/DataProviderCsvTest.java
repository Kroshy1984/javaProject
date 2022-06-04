package ru.sfedu.FatCalculator.dataprovider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import ru.sfedu.FatCalculator.model.*;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.*;

public class DataProviderCsvTest extends BaseTest{

    private static final Logger log = LogManager.getLogger(DataProviderCsvTest.class);

    DataProviderCsv dataProviderCsv = new DataProviderCsv();

    @Test
    public void addUserPositive() throws IOException{
        log.debug("addUserTest success");
        User user = createUser(17, "timur", "tokaev");

        assertTrue(dataProviderCsv.addUser(user));
        dataProviderCsv.deleteUserById(user.getId());
    }

    @Test
    public void addUserNegative(){
        log.debug("addUser Test fail");
        User emptyUser = null;

        assertFalse(dataProviderCsv.addUser(emptyUser));
    }

    @Test
    public void getUserByIdPositive() throws IOException{
        log.debug("getUserById success");
        User user = createUser( "timur", "tokaev");
        dataProviderCsv.addUser(user);

        assertEquals(user.getName(), dataProviderCsv.getUserById(user.getId()).get().getName());
        dataProviderCsv.deleteUserById(user.getId());
    }

    @Test
    public void getUserByIdNegative() throws IOException{
        log.debug("getUserById fail");
        User user = createUser(17, "timur", "tokaev");
        dataProviderCsv.addUser(user);

        assertNotEquals(user, dataProviderCsv.getUserById(100));
        dataProviderCsv.deleteUserById(user.getId());
    }

    @Test
    public void updateUserPositive() throws IOException{
        log.debug("updateUser success");
        User User = createUser(17, "timur", "tokaev");
        User User2 = User;
        User2.setSurname("tokarev");
        dataProviderCsv.addUser(User);
        dataProviderCsv.addUser(User2);

        Assert.assertTrue(dataProviderCsv.updateUser(User2, User.getId()));
        dataProviderCsv.deleteUserById(User.getId());
        dataProviderCsv.deleteUserById(User2.getId());
    }

    @Test
    public void updateUserNegative() throws IOException{
        log.debug("updateUser fail");
        User User = createUser(17, "timur", "tokaev");
        dataProviderCsv.addUser(User);

        Assert.assertFalse(dataProviderCsv.updateUser(User, 1));
        dataProviderCsv.deleteUserById(User.getId());
    }

    @Test
    public void deleteUserByIdPositive() throws IOException{
        log.debug("deleteUser success");
        User user = createUser(17, "timur", "tokaev");
        dataProviderCsv.addUser(user);

        assertTrue(dataProviderCsv.deleteUserById(user.getId()));
    }

    @Test
    public void deleteUserByIdNegative() throws IOException{
        log.debug("deleteUser fail");
        User user = createUser(17, "timur", "tokaev");
        dataProviderCsv.addUser(user);

        assertFalse(dataProviderCsv.deleteUserById(100));
        dataProviderCsv.deleteUserById(user.getId());
    }

    @Test
    public void addResultPositive() throws IOException{
        log.debug("addResultTest success");
        Result Result = createResult(11,27, "22.11.2020");

        assertTrue(dataProviderCsv.addResult(Result));
    }

    @Test
    public void addResultNegative(){
        log.debug("addResult Test fail");
        Result emptyResult = null;

        assertFalse(dataProviderCsv.addResult(emptyResult));
    }

    @Test
    public void getResultByIdPositive() throws IOException{
        log.debug("getResultById success");
        Result Result = createResult(17, 33, 11);
        dataProviderCsv.addResult(Result);

        Assert.assertEquals(dataProviderCsv.getResultById(Result.getResultid()).get().getResultid(), Result.getResultid());
        dataProviderCsv.deleteResultById((Result.getResultid()));
    }

    @Test
    public void getResultByIdNegative() throws IOException{
        log.debug("getResultById fail");
        Result Result = createResult(17, 33, 11);
        dataProviderCsv.addResult(Result);

        assertNotEquals(Result, dataProviderCsv.getResultById(100));
        dataProviderCsv.deleteResultById(Result.getResultid());
    }

    @Test
    public void updateResultPositive() throws IOException{
        log.debug("updateResult success");
        Result Result = createResult(17,33, 11);
        Result Result2 = Result;
        Result2.setFatPercentage(22);
        dataProviderCsv.addResult(Result);
        dataProviderCsv.addResult(Result2);

        Assert.assertTrue(dataProviderCsv.updateResult(Result2, Result.getResultid()));
        dataProviderCsv.deleteResultById(Result.getResultid());
        dataProviderCsv.deleteResultById(Result2.getResultid());
    }

    @Test
    public void updateResultNegative() throws IOException{
        log.debug("updateResult fail");
        Result Result = createResult(17,33, 11);
        dataProviderCsv.addResult(Result);

        Assert.assertFalse(dataProviderCsv.updateResult(Result, 1));
        dataProviderCsv.deleteResultById(Result.getResultid());
    }

    @Test
    public void deleteResultByIdPositive() throws IOException{
        log.debug("deleteResult success");
        Result Result = createResult(17, 33, 11);
        dataProviderCsv.addResult(Result);

        assertTrue(dataProviderCsv.deleteResultById(Result.getResultid()));
    }

    @Test
    public void deleteResultByIdNegative() throws IOException{
        log.debug("deleteResult fail");
        Result Result = createResult(17,33, 11);
        dataProviderCsv.addResult(Result);

        assertFalse(dataProviderCsv.deleteResultById(100));
        dataProviderCsv.deleteResultById((Result.getResultid()));
    }

    @Test
    public void addTapePositive() throws IOException{
        log.debug("addTapeTest success");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);

        assertTrue(dataProviderCsv.addTape(Tape));
        dataProviderCsv.deleteTapeById(Tape.getMeasurementId());
    }

    @Test
    public void addTapeNegative(){
        log.debug("addTape Test fail");
        Tape emptyTape = null;

        assertFalse(dataProviderCsv.addTape(emptyTape));
    }

    @Test
    public void getTapeByIdPositive() throws IOException{
        log.debug("getTapeById success");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderCsv.addTape(Tape);

        assertEquals(Tape.getAge(), dataProviderCsv.getTapeById(Tape.getMeasurementId()).get().getAge());
        dataProviderCsv.deleteTapeById(Tape.getMeasurementId());
    }

    @Test
    public void getTapeByIdNegative() throws IOException{
        log.debug("getTapeById fail");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderCsv.addTape(Tape);

        assertNotEquals(Tape, dataProviderCsv.getTapeById(100));
        dataProviderCsv.deleteTapeById(Tape.getMeasurementId());
    }

    @Test
    public void updateTapePositive() throws IOException{
        log.debug("updateTape success");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        Tape Tape2 = Tape;
        Tape2.setAge(22);
        dataProviderCsv.addTape(Tape);
        dataProviderCsv.addTape(Tape2);

        Assert.assertTrue(dataProviderCsv.updateTape(Tape2, Tape.getMeasurementId()));
        dataProviderCsv.deleteTapeById(Tape.getMeasurementId());
        dataProviderCsv.deleteTapeById(Tape2.getMeasurementId());
    }

    @Test
    public void updateTapeNegative() throws IOException{
        log.debug("updateTape fail");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderCsv.addTape(Tape);

        Assert.assertFalse(dataProviderCsv.updateTape(Tape, 1));
        dataProviderCsv.deleteTapeById(Tape.getMeasurementId());
    }
    @Test
    public void deleteTapeByIdPositive() throws IOException{
        log.debug("deleteTape success");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderCsv.addTape(Tape);

        assertTrue(dataProviderCsv.deleteTapeById(Tape.getMeasurementId()));
        dataProviderCsv.deleteTapeById(Tape.getMeasurementId());
    }

    @Test
    public void deleteTapeByIdNegative() throws IOException{
        log.debug("deleteTape fail");
        Tape Tape = createTape(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderCsv.addTape(Tape);

        assertFalse(dataProviderCsv.deleteTapeById(100));
        dataProviderCsv.deleteTapeById(Tape.getMeasurementId());
    }

    @Test
    public void addThreeSiteSkinFoldPositive() throws IOException{
        log.debug("addThreeSiteSkinFoldTest success");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22,22, 22, 22, 22, 17, "male", 21, 165, 65);

        assertTrue(dataProviderCsv.addThreeSiteSkinFold(ThreeSiteSkinFold));
        dataProviderCsv.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
    }

    @Test
    public void addThreeSiteSkinFoldNegative(){
        log.debug("addThreeSiteSkinFold Test fail");
        ThreeSiteSkinFold emptyThreeSiteSkinFold = null;

        assertFalse(dataProviderCsv.addThreeSiteSkinFold(emptyThreeSiteSkinFold));
    }

    @Test
    public void getThreeSiteSkinFoldByIdPositive() throws IOException{
        log.debug("getThreeSiteSkinFoldById success");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22,22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderCsv.addThreeSiteSkinFold(ThreeSiteSkinFold);

        assertEquals(ThreeSiteSkinFold.getAge(), dataProviderCsv.getThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId()).get().getAge());
        dataProviderCsv.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
    }

    @Test
    public void getThreeSiteSkinFoldByIdNegative() throws IOException{
        log.debug("getThreeSiteSkinFoldById fail");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22,22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderCsv.addThreeSiteSkinFold(ThreeSiteSkinFold);

        assertNotEquals(ThreeSiteSkinFold, dataProviderCsv.getThreeSiteSkinFoldById(100));
        dataProviderCsv.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
    }

    @Test
    public void updateThreeSiteSkinFoldPositive() throws IOException{
        log.debug("updateThreeSiteSkinFold success");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22,22, 22, 22, 22, 17, "male", 21, 165, 65);
        ThreeSiteSkinFold ThreeSiteSkinFold2 = ThreeSiteSkinFold;
        ThreeSiteSkinFold2.setAge(22);
        dataProviderCsv.addThreeSiteSkinFold(ThreeSiteSkinFold);
        dataProviderCsv.addThreeSiteSkinFold(ThreeSiteSkinFold2);

        Assert.assertTrue(dataProviderCsv.updateThreeSiteSkinFold(ThreeSiteSkinFold2, ThreeSiteSkinFold.getMeasurementId()));
        dataProviderCsv.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
        dataProviderCsv.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold2.getMeasurementId());
    }

    @Test
    public void updateThreeSiteSkinFoldNegative() throws IOException{
        log.debug("updateThreeSiteSkinFold fail");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22,22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderCsv.addThreeSiteSkinFold(ThreeSiteSkinFold);

        Assert.assertFalse(dataProviderCsv.updateThreeSiteSkinFold(ThreeSiteSkinFold, 1));
        dataProviderCsv.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
    }

    @Test
    public void deleteThreeSiteSkinFoldByIdPositive() throws IOException{
        log.debug("deleteThreeSiteSkinFold success");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22,22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderCsv.addThreeSiteSkinFold(ThreeSiteSkinFold);

        assertTrue(dataProviderCsv.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId()));
    }

    @Test
    public void deleteThreeSiteSkinFoldByIdNegative() throws IOException{
        log.debug("deleteThreeSiteSkinFold fail");
        ThreeSiteSkinFold ThreeSiteSkinFold = createThreeSiteSkinFold(22,22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderCsv.addThreeSiteSkinFold(ThreeSiteSkinFold);

        assertFalse(dataProviderCsv.deleteThreeSiteSkinFoldById(100));
        dataProviderCsv.deleteThreeSiteSkinFoldById(ThreeSiteSkinFold.getMeasurementId());
    }

    @Test
    public void addFourSiteSkinFoldPositive() throws IOException{
        log.debug("addFourSiteSkinFoldTest success");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);

        assertTrue(dataProviderCsv.addFourSiteSkinFold(FourSiteSkinFold));
        dataProviderCsv.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
    }

    @Test
    public void addFourSiteSkinFoldNegative(){
        log.debug("addFourSiteSkinFold Test fail");
        FourSiteSkinFold emptyFourSiteSkinFold = null;

        assertFalse(dataProviderCsv.addFourSiteSkinFold(emptyFourSiteSkinFold));
    }

    @Test
    public void getFourSiteSkinFoldByIdPositive() throws IOException{
        log.debug("getFourSiteSkinFoldById success");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderCsv.addFourSiteSkinFold(FourSiteSkinFold);

        Assert.assertEquals(FourSiteSkinFold.getMeasurementId(), dataProviderCsv.getFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId()).get().getMeasurementId());
        dataProviderCsv.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
    }

    @Test
    public void getFourSiteSkinFoldByIdNegative() throws IOException{
        log.debug("getFourSiteSkinFoldById fail");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderCsv.addFourSiteSkinFold(FourSiteSkinFold);

        assertNotEquals(FourSiteSkinFold, dataProviderCsv.getFourSiteSkinFoldById(100));
        dataProviderCsv.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
    }

    @Test
    public void updateFourSiteSkinFoldPositive() throws IOException{
        log.debug("updateFourSiteSkinFold success");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        FourSiteSkinFold FourSiteSkinFold2 = FourSiteSkinFold;
        FourSiteSkinFold2.setAge(22);
        dataProviderCsv.addFourSiteSkinFold(FourSiteSkinFold);
        dataProviderCsv.addFourSiteSkinFold(FourSiteSkinFold2);

        Assert.assertTrue(dataProviderCsv.updateFourSiteSkinFold(FourSiteSkinFold2, FourSiteSkinFold.getMeasurementId()));
        dataProviderCsv.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
        dataProviderCsv.deleteFourSiteSkinFoldById(FourSiteSkinFold2.getMeasurementId());
    }

    @Test
    public void updateFourSiteSkinFoldNegative() throws IOException{
        log.debug("updateFourSiteSkinFold fail");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderCsv.addFourSiteSkinFold(FourSiteSkinFold);

        Assert.assertFalse(dataProviderCsv.updateFourSiteSkinFold(FourSiteSkinFold, 1));
        dataProviderCsv.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
    }

    @Test
    public void deleteFourSiteSkinFoldByIdPositive() throws IOException{
        log.debug("deleteFourSiteSkinFold success");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderCsv.addFourSiteSkinFold(FourSiteSkinFold);

        assertTrue(dataProviderCsv.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId()));
        dataProviderCsv.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());
    }

    @Test
    public void deleteFourSiteSkinFoldByIdNegative() throws IOException{
        log.debug("deleteFourSiteSkinFold fail");
        FourSiteSkinFold FourSiteSkinFold = createFourSiteSkinFold(22, 22, 22, 22, 17, "male", 21, 165, 65);
        dataProviderCsv.addFourSiteSkinFold(FourSiteSkinFold);

        assertFalse(dataProviderCsv.deleteFourSiteSkinFoldById(100));
        dataProviderCsv.deleteFourSiteSkinFoldById(FourSiteSkinFold.getMeasurementId());

    }

    @Test
    public void measureFatPositive() throws Exception {
        log.debug("measureFat success");
        assertTrue(dataProviderCsv.measureFat("Tape", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, true));
        dataProviderCsv.deleteAll();
    }

    @Test
    public void measureFatNegative() throws Exception {
        log.debug("measureFat failed");
        assertFalse(dataProviderCsv.measureFat("Tap", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, true));
    }

    @Test
    public void analysisPositive() throws Exception {
        dataProviderCsv.measureFat("Tape", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, false);
        dataProviderCsv.measureFat("Tape", 28282, "timur", "tokaev", 63, 179, 20, "m", 62, 27, 0, 0, false);
        log.debug("analysis success");
        assertTrue(dataProviderCsv.analysis(28282));
        dataProviderCsv.deleteAll();
    }

    @Test
    public void analysisNegative() throws Exception {
        dataProviderCsv.measureFat("Tape", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, false);
        dataProviderCsv.measureFat("Tape", 28282, "timur", "tokaev", 63, 179, 20, "m", 62, 27, 0, 0, false);
        log.debug("analysis failed");
        assertFalse(dataProviderCsv.analysis(28281));
        dataProviderCsv.deleteAll();
    }

    @Test
    public void analysisExtendedPositive() throws Exception {
        dataProviderCsv.measureFat("Tape", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, false);
        dataProviderCsv.measureFat("Tape", 28282, "timur", "tokaev", 63, 179, 20, "m", 62, 27, 0, 0, false);
        log.debug("analysisExtended success");
        assertTrue(dataProviderCsv.analysis(28282, "22.11.2019", "24.03.2021"));
        dataProviderCsv.deleteAll();
    }

    @Test
    public void analysisExtendedNegative() throws Exception {
        dataProviderCsv.measureFat("Tape", 28282, "timur", "tokaev", 66, 179, 20, "m", 64, 28, 0, 0, false);
        dataProviderCsv.measureFat("Tape", 28282, "timur", "tokaev", 63, 179, 20, "m", 62, 27, 0, 0, false);
        log.debug("analysisExtended failed");
        assertFalse(dataProviderCsv.analysis(28282, "22.11.2022", "24.05.2022"));
        dataProviderCsv.deleteAll();
    }

}