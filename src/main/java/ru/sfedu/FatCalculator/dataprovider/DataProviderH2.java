package ru.sfedu.FatCalculator.dataprovider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.FatCalculator.Constants;
import ru.sfedu.FatCalculator.model.*;
import ru.sfedu.FatCalculator.utils.ResultUtil;

import javax.swing.plaf.TableHeaderUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Math.abs;

public class DataProviderH2 implements IDataProvider {
    private static final Logger log = LogManager.getLogger(DataProviderH2.class.getName());
    Connection conn;

    private Statement makeStatement() {
        try {
            conn = DriverManager.getConnection(Constants.H2_URL, Constants.H2_USER_NAME, Constants.H2_USER_PASSWORD);
            return conn.createStatement();
        } catch (Exception e) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public void createTables() {
        try {
            makeStatement().execute(Constants.CREATE_TABLE_USER);
            makeStatement().execute(Constants.CREATE_TABLE_RESULT);
            makeStatement().execute(Constants.CREATE_TABLE_TAPE);
            makeStatement().execute(Constants.CREATE_TABLE_THREESITESKINFOLD);
            makeStatement().execute(Constants.CREATE_TABLE_FOURSITESKINFOLD);

            conn.close();
        } catch (Exception e) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void dropTables() {
        try {
            makeStatement().execute(Constants.DROP_USER_TABLE);
            makeStatement().execute(Constants.DROP_RESULT_TABLE);
            makeStatement().execute(Constants.DROP_TAPE_TABLE);
            makeStatement().execute(Constants.DROP_THREESITESKINFOLD_TABLE);
            makeStatement().execute(Constants.DROP_FOURSITESKINFOLD_TABLE);
            conn.close();
        } catch (Exception e) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    @Override
    public boolean addUser(User User) {
        try {
            String query = String.format(Constants.INSERT_INTO_USER, User.getId(),
                    User.getName(),
                    User.getSurname());
            log.debug("Trying to execute " + query);
            int result = makeStatement().executeUpdate(query);
            log.trace(result);
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUserById(long id) {
        try {
            String query = String.format(Constants.DELETE_FROM_USER, id);
            log.debug("Trying to execute " + query);
            if (makeStatement().executeUpdate(query) == 0) {
                log.error("Cant find such id");
                conn.close();
                return false;
            }
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Optional<User> getUserById(long id) {
        User User = new User();
        try {
            String query = String.format(Constants.SELECT_FROM_USER, id);
            log.debug("Trying to execute " + query);
            ResultSet rs = makeStatement().executeQuery(query);

            while (rs.next()) {
                User.setId(rs.getLong("userid"));
                User.setName(rs.getString("name"));
                User.setSurname(rs.getString("surname"));
            }
            if (User.getId() == 0) {
                conn.close();
                return Optional.empty();
            }
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
        return Optional.of(User);
    }

    @Override
    public boolean updateUser(User User, long id) {
        try {
            if (deleteUserById(id)) {
                addUser(User);
                return true;
            } else {
                log.debug("No value to update");
                return false;
            }
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<Result> getResultById(long id) {
        Result Result = new Result();
        try {
            String query = String.format(Constants.SELECT_FROM_RESULT, id);
            log.debug("Trying to execute " + query);
            ResultSet rs = makeStatement().executeQuery(query);

            while (rs.next()) {
                Result.setResultid(rs.getLong("result_id"));
                Result.setUserid(rs.getLong("userid"));
                Result.setDate(rs.getString("date"));
                Result.setFatPercentage(rs.getDouble("fat_percentage"));
                Result.setStage(Stage.valueOf((rs.getString("stage"))));
            }
            if (Result.getResultid() == 0) {
                conn.close();
                return Optional.empty();
            }
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
        return Optional.of(Result);
    }

    @Override
    public boolean updateResult(Result Result, long id) {
        try {
            if (deleteResultById(id)) {
                addResult(Result);
                return true;
            } else {
                log.debug("No value to update");
                return false;
            }
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean addResult(Result Result) {
        try {
            String query = String.format(Constants.INSERT_INTO_RESULT, Result.getResultid(),
                    Result.getUserid(),
                    Result.getDate(),
                    Result.getFatPercentage(),
                    Result.getStage());

            log.debug("Trying to execute " + query);
            int result = makeStatement().executeUpdate(query);
            log.trace(result);
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteResultById(long id) {
        try {
            String query = String.format(Constants.DELETE_FROM_RESULT, id);
            log.debug("Trying to execute " + query);
            if (makeStatement().executeUpdate(query) == 0) {
                log.error("Cant find such id");
                conn.close();
                return false;
            }
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Optional<Tape> getTapeById(long id) {
        Tape Tape = new Tape();
        try {
            String query = String.format(Constants.SELECT_FROM_TAPE, id);
            log.debug("Trying to execute " + query);
            ResultSet rs = makeStatement().executeQuery(query);

            while (rs.next()) {
                Tape.setMeasurementId(rs.getLong("measurement_id"));
                Tape.setUserid(rs.getLong("userid"));
                Tape.setWeight(rs.getDouble("weight"));
                Tape.setHeight(rs.getDouble("height"));
                Tape.setAge(rs.getInt("age"));
                Tape.setGender(rs.getString("gender"));
                Tape.setNeck(rs.getDouble("neck"));
                Tape.setWaist(rs.getDouble("waist"));
                Tape.setHips(rs.getDouble("hips"));
                Tape.setAbdomen(rs.getDouble("abdomen"));

            }
            if (Tape.getMeasurementId() == 0) {
                conn.close();
                return Optional.empty();
            }
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
        return Optional.of(Tape);
    }

    @Override
    public boolean updateTape(Tape Tape, long id) {
        try {
            if (deleteTapeById(id)) {
                addTape(Tape);
                return true;
            } else {
                log.debug("No value to update");
                return false;
            }
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean addTape(Tape Tape) {
        try {
            String query = String.format(Constants.INSERT_INTO_TAPE,
                    Tape.getMeasurementId(),
                    Tape.getUserid(),
                    Tape.getWeight(),
                    Tape.getHeight(),
                    Tape.getAge(),
                    Tape.getGender(),
                    Tape.getNeck(),
                    Tape.getWaist(),
                    Tape.getHips(),
                    Tape.getAbdomen());

            log.debug("Trying to execute " + query);
            int result = makeStatement().executeUpdate(query);
            log.trace(result);
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteTapeById(long id) {
        try {
            String query = String.format(Constants.DELETE_FROM_TAPE, id);
            log.debug("Trying to execute " + query);
            if (makeStatement().executeUpdate(query) == 0) {
                log.error("Cant find such id");
                conn.close();
                return false;
            }
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Optional<ThreeSiteSkinFold> getThreeSiteSkinFoldById(long id) {
        ThreeSiteSkinFold ThreeSiteSkinFold = new ThreeSiteSkinFold();
        try {
            String query = String.format(Constants.SELECT_FROM_THREESITESKINFOLD, id);
            log.debug("Trying to execute " + query);
            ResultSet rs = makeStatement().executeQuery(query);

            while (rs.next()) {
                ThreeSiteSkinFold.setMeasurementId(rs.getLong("measurement_id"));
                ThreeSiteSkinFold.setUserid(rs.getLong("userid"));
                ThreeSiteSkinFold.setWeight(rs.getDouble("weight"));
                ThreeSiteSkinFold.setHeight(rs.getDouble("height"));
                ThreeSiteSkinFold.setAge(rs.getInt("age"));
                ThreeSiteSkinFold.setGender(rs.getString("gender"));
                ThreeSiteSkinFold.setChest(rs.getDouble("chest"));
                ThreeSiteSkinFold.setThigh(rs.getDouble("thigh"));
                ThreeSiteSkinFold.setTricep(rs.getDouble("tricep"));
                ThreeSiteSkinFold.setSuprailiac(rs.getDouble("suprailiac"));
                ThreeSiteSkinFold.setAbdominal(rs.getDouble("abdominal"));
            }
            if (ThreeSiteSkinFold.getMeasurementId() == 0) {
                conn.close();
                return Optional.empty();
            }
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
        return Optional.of(ThreeSiteSkinFold);
    }

    @Override
    public boolean updateThreeSiteSkinFold(ThreeSiteSkinFold ThreeSiteSkinFold, long id) {
        try {
            if (deleteThreeSiteSkinFoldById(id)) {
                addThreeSiteSkinFold(ThreeSiteSkinFold);
                return true;
            } else {
                log.debug("No value to update");
                return false;
            }
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }


    @Override
    public boolean addThreeSiteSkinFold(ThreeSiteSkinFold ThreeSiteSkinFold) {
        try {
            String query = String.format(Constants.INSERT_INTO_THREESITESKINFOLD,
                    ThreeSiteSkinFold.getMeasurementId(),
                    ThreeSiteSkinFold.getUserid(),
                    ThreeSiteSkinFold.getWeight(),
                    ThreeSiteSkinFold.getHeight(),
                    ThreeSiteSkinFold.getAge(),
                    ThreeSiteSkinFold.getGender(),
                    ThreeSiteSkinFold.getChest(),
                    ThreeSiteSkinFold.getThigh(),
                    ThreeSiteSkinFold.getTricep(),
                    ThreeSiteSkinFold.getSuprailiac(),
                    ThreeSiteSkinFold.getAbdominal());

            log.debug("Trying to execute " + query);
            int result = makeStatement().executeUpdate(query);
            log.trace(result);
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteThreeSiteSkinFoldById(long id) {
        try {
            String query = String.format(Constants.DELETE_FROM_THREESITESKINFOLD, id);
            log.debug("Trying to execute " + query);
            if (makeStatement().executeUpdate(query) == 0) {
                log.error("Cant find such id");
                conn.close();
                return false;
            }
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Optional<FourSiteSkinFold> getFourSiteSkinFoldById(long id) {
        FourSiteSkinFold FourSiteSkinFold = new FourSiteSkinFold();
        try {
            String query = String.format(Constants.SELECT_FROM_FOURSITESKINFOLD, id);
            log.debug("Trying to execute " + query);
            ResultSet rs = makeStatement().executeQuery(query);

            while (rs.next()) {
                FourSiteSkinFold.setMeasurementId(rs.getLong("measurement_id"));
                FourSiteSkinFold.setUserid(rs.getLong("userid"));
                FourSiteSkinFold.setWeight(rs.getDouble("weight"));
                FourSiteSkinFold.setHeight(rs.getDouble("height"));
                FourSiteSkinFold.setAge(rs.getInt("age"));
                FourSiteSkinFold.setGender(rs.getString("gender"));
                FourSiteSkinFold.setThigh(rs.getDouble("thigh"));
                FourSiteSkinFold.setTricep(rs.getDouble("tricep"));
                FourSiteSkinFold.setSuprailiac(rs.getDouble("suprailiac"));
                FourSiteSkinFold.setAbdominal(rs.getDouble("abdominal"));
            }
            if (FourSiteSkinFold.getMeasurementId() == 0) {
                conn.close();
                return Optional.empty();
            }
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
        return Optional.of(FourSiteSkinFold);
    }

    @Override
    public boolean updateFourSiteSkinFold(FourSiteSkinFold FourSiteSkinFold, long id) {
        try {
            if (deleteFourSiteSkinFoldById(id)) {
                addFourSiteSkinFold(FourSiteSkinFold);
                return true;
            } else {
                log.debug("No value to update");
                return false;
            }
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }


    @Override
    public boolean addFourSiteSkinFold(FourSiteSkinFold FourSiteSkinFold) {
        try {
            String query = String.format(Constants.INSERT_INTO_FOURSITESKINFOLD,
                    FourSiteSkinFold.getMeasurementId(),
                    FourSiteSkinFold.getUserid(),
                    FourSiteSkinFold.getWeight(),
                    FourSiteSkinFold.getHeight(),
                    FourSiteSkinFold.getAge(),
                    FourSiteSkinFold.getGender(),
                    FourSiteSkinFold.getThigh(),
                    FourSiteSkinFold.getTricep(),
                    FourSiteSkinFold.getSuprailiac(),
                    FourSiteSkinFold.getAbdominal());

            log.debug("Trying to execute " + query);
            int result = makeStatement().executeUpdate(query);
            log.trace(result);
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteFourSiteSkinFoldById(long id) {
        try {
            String query = String.format(Constants.DELETE_FROM_FOURSITESKINFOLD, id);
            log.debug("Trying to execute " + query);
            if (makeStatement().executeUpdate(query) == 0) {
                log.error("Cant find such id");
                conn.close();
                return false;
            }
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    public Optional<List<User>> getAllUsers() {
        User User = new User();
        List<User> ListUser = new ArrayList<>();
        try {
            String query = Constants.SELECT_FROM_USER_ALL;
            log.debug("Trying to execute " + query);
            ResultSet rs = makeStatement().executeQuery(query);

            while (rs.next()) {
                User.setId(rs.getLong("userid"));
                User.setName(rs.getString("name"));
                User.setSurname(rs.getString("surname"));
                ListUser.add(User);
            }
            if (User.getId() == 0) {
                conn.close();
                return Optional.empty();
            }
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
        return Optional.of(ListUser);
    }

    public Optional<List<Result>> getAllResults() {
        Result Result = new Result();
        List<Result> ListResult = new ArrayList<>();
        try {
            String query = Constants.SELECT_FROM_RESULT_ALL;
            log.debug("Trying to execute " + query);
            ResultSet rs = makeStatement().executeQuery(query);

            while (rs.next()) {
                Result.setResultid(rs.getLong("result_id"));
                Result.setUserid(rs.getLong("userid"));
                Result.setDate(rs.getString("date"));
                Result.setFatPercentage(rs.getDouble("fat_percentage"));
                Result.setStage(Stage.valueOf((rs.getString("stage"))));
            }
            if (Result.getResultid() == 0) {
                conn.close();
                return Optional.empty();
            }
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
        return Optional.of(ListResult);
    }

    public Optional<List<Tape>> getAllTapes() {
        Tape Tape = new Tape();
        List<Tape> ListTape = new ArrayList<>();
        try {
            String query = Constants.SELECT_FROM_TAPE_ALL;
            log.debug("Trying to execute " + query);
            ResultSet rs = makeStatement().executeQuery(query);

            while (rs.next()) {
                Tape.setMeasurementId(rs.getLong("measurement_id"));
                Tape.setUserid(rs.getLong("userid"));
                Tape.setWeight(rs.getDouble("weight"));
                Tape.setHeight(rs.getDouble("height"));
                Tape.setAge(rs.getInt("age"));
                Tape.setGender(rs.getString("gender"));
                Tape.setNeck(rs.getDouble("neck"));
                Tape.setWaist(rs.getDouble("waist"));
                Tape.setHips(rs.getDouble("hips"));
                Tape.setAbdomen(rs.getDouble("abdomen"));
                ListTape.add(Tape);
            }
            if (Tape.getMeasurementId() == 0) {
                conn.close();
                return Optional.empty();
            }
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
        return Optional.of(ListTape);
    }

    public Optional<List<ThreeSiteSkinFold>> getAllThreeSiteSkinFolds() {
        ThreeSiteSkinFold ThreeSiteSkinFold = new ThreeSiteSkinFold();
        List<ThreeSiteSkinFold> ListThreeSiteSkinFold = new ArrayList<>();
        try {
            String query = Constants.SELECT_FROM_THREESITESKINFOLD_ALL;
            log.debug("Trying to execute " + query);
            ResultSet rs = makeStatement().executeQuery(query);

            while (rs.next()) {
                ThreeSiteSkinFold.setMeasurementId(rs.getLong("measurement_id"));
                ThreeSiteSkinFold.setUserid(rs.getLong("userid"));
                ThreeSiteSkinFold.setWeight(rs.getDouble("weight"));
                ThreeSiteSkinFold.setHeight(rs.getDouble("height"));
                ThreeSiteSkinFold.setAge(rs.getInt("age"));
                ThreeSiteSkinFold.setGender(rs.getString("gender"));
                ThreeSiteSkinFold.setChest(rs.getDouble("chest"));
                ThreeSiteSkinFold.setThigh(rs.getDouble("thigh"));
                ThreeSiteSkinFold.setTricep(rs.getDouble("tricep"));
                ThreeSiteSkinFold.setSuprailiac(rs.getDouble("suprailiac"));
                ThreeSiteSkinFold.setAbdominal(rs.getDouble("abdominal"));
                ListThreeSiteSkinFold.add(ThreeSiteSkinFold);
            }
            if (ThreeSiteSkinFold.getMeasurementId() == 0) {
                conn.close();
                return Optional.empty();
            }
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
        return Optional.of(ListThreeSiteSkinFold);
    }

    public Optional<List<FourSiteSkinFold>> getAllFourSiteSkinFolds() {
        FourSiteSkinFold FourSiteSkinFold = new FourSiteSkinFold();
        List<FourSiteSkinFold> ListFourSiteSkinFold = new ArrayList<>();
        try {
            String query = Constants.SELECT_FROM_FOURSITESKINFOLD_ALL;
            log.debug("Trying to execute " + query);
            ResultSet rs = makeStatement().executeQuery(query);

            while (rs.next()) {
                FourSiteSkinFold.setMeasurementId(rs.getLong("measurement_id"));
                FourSiteSkinFold.setUserid(rs.getLong("userid"));
                FourSiteSkinFold.setWeight(rs.getDouble("weight"));
                FourSiteSkinFold.setHeight(rs.getDouble("height"));
                FourSiteSkinFold.setAge(rs.getInt("age"));
                FourSiteSkinFold.setGender(rs.getString("gender"));
                FourSiteSkinFold.setThigh(rs.getDouble("thigh"));
                FourSiteSkinFold.setTricep(rs.getDouble("tricep"));
                FourSiteSkinFold.setSuprailiac(rs.getDouble("suprailiac"));
                FourSiteSkinFold.setAbdominal(rs.getDouble("abdominal"));
                ListFourSiteSkinFold.add(FourSiteSkinFold);
            }
            if (FourSiteSkinFold.getMeasurementId() == 0) {
                conn.close();
                return Optional.empty();
            }
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
        return Optional.of(ListFourSiteSkinFold);
    }

    public boolean measureFat(String measurement, long userid, String name, String surname, double weight, double height,
                              int age, String gender, double a, double b, double c, double d, boolean info) throws Exception {
        log.debug("Start measureFat");
        try {
            User user = new User(userid, name, surname);

            Measurement m = selectionMeasurement(measurement, gender, a, b, c, d);
            if(m.getGender() != null)
                throw new Exception("selectionMeasurement Error!");
            m.setCommon(userid, weight, height, age, gender);
            double percentage = getFatPercentage(measurement, gender, height, age, a, b, c, d);
            if (percentage == 0){
                throw new Exception("getFatPercentage Error!");
            }
            Result result = new Result(userid, percentage, getStage(gender, percentage));
            addUser(user);
            if (measurement.equals("Tape"))
                addTape((Tape) m);
            else if (measurement.equals("ThreeSiteSkinFold"))
                addThreeSiteSkinFold((ThreeSiteSkinFold) m);
            else
                addFourSiteSkinFold((FourSiteSkinFold) m);
            addResult(result);
            if(info)
                getInfo(name, surname, weight, height, age, percentage, getStage(gender, percentage));
            return true;
        }
        catch(Exception e) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public void getInfo(String name, String surname, double weight, double height, int age,
                        double fatPercentage, Stage stage) {
        log.debug("Start getInfo");
        switch (stage) {
            case UNDERWEIGHT:
                log.info("{} {} at the age of {}, with a weight of {} kg and a height of {} cm, your body " +
                                "fat percentage is {}%, which is not the norm for your characteristics, your weight is not " +
                                "big enough, you need to gain weight. Emphasis should be placed on foods that are rich in " +
                                "nutrients. However, in order to gain weight, you should not abuse sweets, as well as fast " +
                                "food, soda, pastries, too fatty and salty foods..", name, surname, age, weight, height,
                        fatPercentage);
                break;
            case HEAlTHY:
                log.info("{} {} at the age of {}, with a weight of {} kg and a height of {} cm, your" +
                        " body fat percentage is {}%, which is the norm for your characteristics. Continue to lead a" +
                        " healthy lifestyle and you will be fine.", name, surname, age, weight, height, fatPercentage);
                break;
            case OVERWEIGHT:
                log.info("{} {} at the age of {}, with a weight of {} kg and a height of {} cm, your body " +
                        "fat percentage is {}%, which is not the norm for your characteristics, you are overweight. " +
                        "To combat excess weight, you can use two methods - increase physical activity or reduce the " +
                        "amount of energy entering the body. To achieve the task in a shorter time, it is recommended" +
                        " to play sports.", name, surname, age, weight, height, fatPercentage);
                break;
            case OBESE:
                log.info("{} {} at the age of {}, with a weight of {} kg and a height of {} cm, your body " +
                                "fat percentage is {}%, which is not the norm for your characteristics, you are in a state of" +
                                " obesity. We advise you to contact a specialist and worry about this problem.", name, surname,
                        age, weight, height, fatPercentage);
                break;
            case EXTREMELY_OBESE:
                log.info("{} {} at the age of {}, with a weight of {} kg and a height of {} cm, your body " +
                                "fat percentage is {}%, which is not the norm for your characteristics, you are in a state of" +
                                " neglected obesity. We urge you to contact a specialist as soon as possible..", name, surname,
                        age, weight, height, fatPercentage);
                break;
        }
    }

    public Stage getStage(String gen, double fatPercentage) {
        log.debug("Start getStage");
        if (gen.equals("m")) {
            if (fatPercentage < 8)
                return (Stage.UNDERWEIGHT);
            else if (fatPercentage >= 8 && fatPercentage < 19)
                return (Stage.HEAlTHY);
            else if (fatPercentage >= 19 && fatPercentage < 25)
                return (Stage.OVERWEIGHT);
            else if (fatPercentage >= 25 && fatPercentage < 30)
                return (Stage.OBESE);
            else
                return (Stage.EXTREMELY_OBESE);
        } else {
            if (fatPercentage < 21)
                return (Stage.UNDERWEIGHT);
            else if (fatPercentage >= 21 && fatPercentage < 33)
                return (Stage.HEAlTHY);
            else if (fatPercentage >= 33 && fatPercentage < 39)
                return (Stage.OVERWEIGHT);
            else if (fatPercentage >= 39 && fatPercentage < 44)
                return (Stage.OBESE);
            else
                return (Stage.EXTREMELY_OBESE);
        }
    }

    public double getFatPercentage(String measurement, String gender, double height, int age,
                                   double a, double b, double c, double d) throws Exception {
        log.debug("Start getFatPercentage");
        double fatPercentage, bodyDensity;
        try {
            if (measurement.equals("Tape")) {
                if (gender.equals("m")) {
                    if (a == 0 || b == 0 || height == 0)
                        throw new Exception("required characteristics are not entered! Gender: m, Measurement: Tape");
                    fatPercentage = 86.01 * log10(a - b) - 70.041 * log10(height) + 36.76;
                } else {
                    if (a == 0 || b == 0 || c == 0 || height == 0)
                        throw new Exception("required characteristics are not entered! Gender: f, Measurement: Tape");
                    fatPercentage = 163.205 * log10(b + c - a) - 97.684 * log10(height) - 78.387;
                }
            } else if (measurement.equals("ThreeSiteSkinFold")) {
                if (gender.equals("m")) {
                    if (a == 0 || b == 0 || c == 0 || age == 0)
                        throw new Exception("required characteristics are not entered! Gender: m, Measurement: ThreeSiteSkinFold");
                    bodyDensity = 1.10938 - (0.0008276 * (c + a + b)) + (0.0000016 * ((c + a + b) * (c + a + b)) - (0.0002574 * age));
                } else {
                    if (a == 0 || b == 0 || c == 0)
                        throw new Exception("required characteristics are not entered! Gender: f, Measurement: ThreeSiteSkinFold");
                    bodyDensity = 1.0994921 - (0.0009929 * (c + a + b)) + (0.0000023 * ((c + a + b) * (c + a + b)) - (0.0001392 * age));
                }
                fatPercentage = (495 / bodyDensity) - 450;
            } else {
                if (gender.equals("m")) {
                    if (a == 0 || b == 0 || c == 0 || d == 0 || age == 0)
                        throw new Exception("required characteristics are not entered! Gender: m, Measurement: FourSiteSkinFold");
                    bodyDensity = (0.29288 * (a + b + c + d)) - (0.0005 * ((a + b + c + d) * (a + b + c + d))) + (0.15845 * age) - 5.76377;
                } else {
                    if (a == 0 || b == 0 || c == 0 || d == 0 || age == 0)
                        throw new Exception("required characteristics are not entered! Gender: f, Measurement: FourSiteSkinFold");
                    bodyDensity = (0.29669 * (a + b + c + d)) - (0.00043 * ((a + b + c + d) * (a + b + c + d))) + (0.2963 * age) + 1.4072;
                }
                fatPercentage = (495 / bodyDensity) - 450;
            }
            return fatPercentage;
        } catch (Exception e) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return 0;
        }
    }

    public Measurement selectionMeasurement(String measurement, String gender, double a, double b, double c, double d) throws Exception {
        log.debug("Start selectionMeasurement");
        try {
            if (gender.equals("m")) {
                return switch (measurement) {
                    case "Tape" -> new Tape(a, b);
                    case "ThreeSiteSkinFold" -> new ThreeSiteSkinFold(a, b, c);
                    case "FourSiteSkinFold" -> new FourSiteSkinFold(a, b, c, d);
                    default -> throw new Exception("introduced non-existent method!");
                };
            }
            if (gender.equals("f")) {
                return switch (measurement) {
                    case "Tape" -> new Tape(a, b, c);
                    case "ThreeSiteSkinFold" -> new ThreeSiteSkinFold(a, b, c, 0);
                    case "FourSiteSkinFold" -> new FourSiteSkinFold(a, b, c, d);
                    default -> throw new Exception("introduced non-existent method!");
                };
            } else {
                throw new Exception("gender is incorrect");
            }
        } catch (Exception e) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return new Measurement(gender);
        }
    } 

    public List<Result> getAllResultById(long id) {
        Result Result = new Result();
        List<Result> ResultList = new ArrayList<>();
        try {
            String query = String.format(Constants.SELECT_FROM_RESULT_ALL_USER, id);
            log.debug("Trying to execute " + query);
            ResultSet rs = makeStatement().executeQuery(query);

            while (rs.next()) {
                Result.setResultid(rs.getLong("result_id"));
                Result.setUserid(rs.getLong("userid"));
                Result.setDate(rs.getString("date"));
                Result.setFatPercentage(rs.getDouble("fat_percentage"));
                Result.setStage(Stage.valueOf((rs.getString("stage"))));
                ResultList.add(Result);
            }
            if (ResultList.size() == 0) {
                conn.close();
                return null;
            }
            conn.close();
        } catch (Exception e) {
            log.debug(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
        log.debug("ResultList " + ResultList);
        return ResultList;
    }
    public boolean analysis(long userid) throws ParseException {
        log.debug("Start analysis");
        List<Result> ResultList = getAllResultById(userid);
        List <Date> ResultDate = new ArrayList<>();
        try {
            ResultList.forEach((res) -> {
                try {
                    ResultDate.add(ResultUtil.changeStringDate(res.getDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
            log.debug("ResultDate" + ResultDate);
            Date FirstDate = ResultDate.stream().min(Date::compareTo).get();
            Date LastDate = ResultDate.stream().max(Date::compareTo).get();

            int Term = (int) ((LastDate.getTime() - FirstDate.getTime()) / (24 * 60 * 60 * 1000));
            String FDate = ResultUtil.changeDateString(FirstDate);
            String LDate = ResultUtil.changeDateString(LastDate);

            double FPer = ResultList.stream().filter(bean -> Objects.equals(bean.getDate(), FDate)).
                    findFirst().get().getFatPercentage();
            double LPer = ResultList.stream().filter(bean -> Objects.equals(bean.getDate(), LDate)).
                    findFirst().get().getFatPercentage();
            output(FDate, LDate, FPer, LPer, Term);
            return true;
        }
        catch (Exception e){
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public boolean analysis(long userid, String firstDate, String secondDate) throws ParseException {
        try{
            log.debug("Start analysis");
            List<Result> ResultList = getAllResultById(userid);
            List<String> Dates = getDates(userid, firstDate, secondDate);
            Date FirstDate = ResultUtil.changeStringDate(Dates.get(0));
            Date LastDate = ResultUtil.changeStringDate(Dates.get(1));

            int Term = (int) ((LastDate.getTime() - FirstDate.getTime()) / (24 * 60 * 60 * 1000));
            String FDate = ResultUtil.changeDateString(FirstDate);
            String LDate = ResultUtil.changeDateString(LastDate);

            double FPer = ResultList.stream().filter(bean -> Objects.equals(bean.getDate(), FDate)).
                    findFirst().get().getFatPercentage();
            double LPer = ResultList.stream().filter(bean -> Objects.equals(bean.getDate(), LDate)).
                    findFirst().get().getFatPercentage();
            output(FDate, LDate, FPer, LPer, Term);
            return true;
        }
        catch (Exception e){
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public List<String> getDates(long userid, String d1, String d2) {
        log.debug("Start getDates");
        List<Result> ResultList = getAllResultById(userid);
        List<LocalDateTime> ResultDate = new ArrayList<>();
        try {
            log.debug("Ended getDates");
            log.debug(ResultUtil.changeStringLocalDate(ResultList.get(1).getDate()));
            ResultList.forEach((res) -> {
                try {
                    ResultDate.add(ResultUtil.changeStringLocalDate(res.getDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
            log.debug("Ended getDates");
            return findDates(ResultDate, d1, d2);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> findDates(List<LocalDateTime> resultDate, String d1, String d2) throws ParseException {
        log.debug("Start findDates");
        LocalDateTime Date1 = ResultUtil.changeStringLocalDate(d1);
        LocalDateTime Date2 = ResultUtil.changeStringLocalDate(d2);
        LocalDateTime FDate, LDate;
        if (resultDate.stream().anyMatch(d -> d == Date1)) {
            FDate = Date1;
        } else {
            FDate = resultDate.stream()
                    .filter(date -> date.toLocalDate().isAfter(Date1.toLocalDate()))
                    .min(LocalDateTime::compareTo)
                    .get();
        }
        if (resultDate.stream().anyMatch(d -> d == Date2)) {
            LDate = Date2;
        } else {
            LDate = resultDate.stream()
                    .filter(date -> date.toLocalDate().isAfter(Date2.toLocalDate()))
                    .min(LocalDateTime::compareTo)
                    .get();
        }
        List<String> res = new ArrayList<>();
        res.add(ResultUtil.changeLocalDateString(FDate));
        res.add(ResultUtil.changeLocalDateString(LDate));
        return res;
    }

    public void output(String FDate, String LDate, double FPer, double LPer, int term) {
        log.debug("Start output");
        if (LPer - FPer > 0) {
            log.info("Your first measurement was on {}, the body fat percentage was {}%. Your last measurement" +
                            " was {}, the percentage of body fat is {}%. After {} days, you have lost {}% fat.", FDate, FPer,
                    LDate, LPer, term, abs(LPer - FPer));
        } else {
            log.info("Your first measurement was on {}, the body fat percentage was {}%. Your last measurement" +
                            " was {}, the percentage of body fat is {}%. After {} days, you have gained {}% fat.", FDate, FPer,
                    LDate, LPer, term, abs(LPer - FPer));
        }
    }
}