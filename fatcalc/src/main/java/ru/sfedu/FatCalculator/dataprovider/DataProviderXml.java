package ru.sfedu.FatCalculator.dataprovider;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.FatCalculator.model.*;
import ru.sfedu.FatCalculator.utils.ConfigurationUtil;
import ru.sfedu.FatCalculator.Constants;
import ru.sfedu.FatCalculator.utils.ResultUtil;
import ru.sfedu.FatCalculator.utils.XMLUtil;


import java.io.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.Math.*;
import static java.lang.Math.abs;

public class DataProviderXml implements IDataProvider{

    private static final Logger log = LogManager.getLogger(DataProviderXml.class);

    private <T> ArrayList<T> loadBeanXML(String path){
        log.debug("Running loadBeansXML...");
        ArrayList<T> loadedBeans = null;
        XMLUtil<T> xml;
        try {
            Serializer serializer = new Persister();
            log.debug("Start reading file from " + path);
            File file = new File(ConfigurationUtil.getConfigurationEntry(path));
            log.debug("Ended reading file from " + ConfigurationUtil.getConfigurationEntry(path));
            if (file.length()!=0) {
                xml = serializer.read(XMLUtil.class, file);
                loadedBeans = (ArrayList<T>) xml.getList();
            }
            else {
                loadedBeans = new ArrayList<>();
            }
            log.debug("Loaded list looks like: " + loadedBeans.toString());
        }
        catch(Exception e){
            log.error("Loading Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
        return loadedBeans;
    }

    private <T> boolean writeXML(List<T> list){
        try {
            Serializer serializer = new Persister();
            log.debug("Will write to " + ConfigurationUtil.
                    getConfigurationEntry(findPath(list)));
            File result = new File(ConfigurationUtil.
                    getConfigurationEntry(findPath(list)));
            Writer writer = new FileWriter(result);
            XMLUtil<T> xml = new XMLUtil<>(list);
            serializer.write(xml, writer);
            log.trace("Writing ended up successfully");
        }
        catch (Exception e){
            log.error("Writing file Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    private  <T> String findPath(List<T> list){
        log.debug("Class of elements inside list: "+list.get(0).getClass().getSimpleName());
        return switch (list.get(0).getClass().getSimpleName()) {
            case "Result" -> Constants.RESULT_XML_PATH;
            case "Tape" -> Constants.TAPE_XML_PATH;
            case "User" -> Constants.USER_XML_PATH;
            case "ThreeSiteSkinFold" -> Constants.THREESITESKINFOLD_XML_PATH;
            case "FourSiteSkinFold" -> Constants.FOURSITESKINFOLD_XML_PATH;
            default -> Constants.UNKNOWN_SOURCE;
        };
    }

    public Optional<User> getUserById(long id) {
        User UserBean;
        List<User> UserList;
        try {
            log.debug("getUserID[1]");
            UserList = loadBeanXML(Constants.USER_XML_PATH);
            UserBean = UserList.stream()
                    .filter(bean -> bean.getId() == id)
                    .findFirst()
                    .get();
            log.trace("Receiving record complete");
            return Optional.of(UserBean);
        }
        catch(Exception e) {
            log.error("Unable to receive User with id = " + id);
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    public boolean addUser(User User) {
        List<User> UserList;
        try {
            log.debug("addUser[1]: ");
            if (User == null) {
                throw new Exception("Adding User error, User equals null");
            }
            UserList = loadBeanXML(Constants.USER_CSV_PATH);
            if (UserList != null) {
                Optional<ru.sfedu.FatCalculator.model.User> UserBean = UserList.stream()
                        .filter(bean -> bean.getId() == User.getId())
                        .findFirst();
                if (UserBean.isPresent()) {
                    log.debug("addUser[2]: replaced bean: " + UserBean);
                    Predicate<User> isDeletable = user -> user.getId() == User.getId();
                    UserList.removeIf(isDeletable);
                }
            } else {
                UserList = new ArrayList<>();
            }
            UserList.add(User);
            writeXML(UserList);

            log.debug("User added[2]: ");
            return true;
        } catch (Exception e) {
            log.error("addUser Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public boolean updateUser(User User, long id) {
        List<User> UserList;
        try {
            log.debug("updateUser[1]");
            UserList = loadBeanXML(Constants.USER_XML_PATH);
            log.debug("Loaded list in upd is[2]: " + UserList);
            User UserBean = UserList.stream()
                    .filter(bean -> bean.getId() == id)
                    .findFirst()
                    .get();
            int index = UserList.indexOf(UserBean);
            UserList.set(index, User);
            writeXML(UserList);
            log.trace("Updating complete[3]");
            return true;
        } catch (Exception e) {
            log.error("updateUser Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public boolean deleteUserById(long id) {
        List<User> UserList;
        try {
            log.debug("deleteUserById[1]:");
            UserList = loadBeanXML(Constants.USER_XML_PATH);
            Predicate<User> isDeletable = User -> User.getId() == id;

            if (UserList.removeIf(isDeletable)) {
                log.trace("Saving");
                if (UserList.isEmpty()) {
                    FileWriter file = new FileWriter(ConfigurationUtil.getConfigurationEntry(Constants.USER_XML_PATH));
                    file.write("");
                    file.close();
                } else
                    writeXML(UserList);
                log.debug("User deleted[2]:");
                return true;
            }
            else {
                log.debug("No record with stated id");
                return false;
            }
        }
        catch (Exception e) {
            log.error("deleteUserById Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public Optional<Result> getResultById(long id) {
        Result ResultBean;
        List<Result> ResultList;
        try {
            log.debug("getResultId[1]");
            ResultList = loadBeanXML(Constants.RESULT_XML_PATH);
            ResultBean = ResultList.stream()
                    .filter(bean -> bean.getResultid() == id)
                    .findFirst()
                    .get();
            log.trace("Receiving record complete");
            return Optional.of(ResultBean);
        }
        catch(Exception e) {
            log.error("Unable to receive Result with id = " + id);
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    public boolean addResult(Result Result) {
        List<Result> resultList;
        try {
            log.debug("addResult[1]: ");
            if (Result == null) {
                throw new Exception("Adding result error, result equals null");
            }
            resultList = loadBeanXML(Constants.RESULT_XML_PATH);
            if (resultList == null){
                resultList = new ArrayList<>();
            }
            log.debug(resultList);
            resultList.add(Result);
            writeXML(resultList);

            log.debug("Result added[2]: ");
            return true;
        } catch (Exception e) {
            log.error("addResult Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public boolean updateResult(Result Result, long id) {
        List<Result> resultList;
        try {
            log.debug("updateResult[1]");
            resultList = loadBeanXML(Constants.RESULT_XML_PATH);
            log.debug("Loaded list in upd is[2]: " + resultList);
            Result resultBean = resultList.stream()
                    .filter(bean -> bean.getResultid() == id)
                    .findFirst()
                    .get();
            int index = resultList.indexOf(resultBean);
            resultList.set(index, Result);
            writeXML(resultList);
            log.trace("Updating complete[3]");
            return true;
        } catch (Exception e) {
            log.error("updateResult Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public boolean deleteResultById(long id) {
        List<Result> resultList;
        try {
            log.debug("deleteResultById[1]:");
            resultList = loadBeanXML(Constants.RESULT_XML_PATH);
            Predicate<Result> isDeletable = result -> result.getResultid() == id;
            log.debug("deleteResultById[1]:" + isDeletable);
            if (resultList.removeIf(isDeletable)) {
                log.trace("Saving");
                if (resultList.isEmpty()) {
                    FileWriter file = new FileWriter(ConfigurationUtil.getConfigurationEntry(Constants.RESULT_XML_PATH));
                    file.write("");
                    file.close();
                } else
                    writeXML(resultList);
                log.debug("Result deleted[2]:");
                return true;
            }
            else {
                log.debug("No record with stated id");
                return false;
            }
        }
        catch (Exception e) {
            log.error("deleteResultById Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public Optional<Tape> getTapeById(long id) {
        Tape TapeBean;
        List<Tape> TapeList;
        try {
            log.debug("getTapeID[1]");
            TapeList = loadBeanXML(Constants.TAPE_XML_PATH);
            TapeBean = TapeList.stream()
                    .filter(bean -> bean.getMeasurementId() == id)
                    .findFirst()
                    .get();
            log.trace("Receiving record complete");
            return Optional.of(TapeBean);
        }
        catch(Exception e) {
            log.error("Unable to receive Tape with id = " + id);
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    public boolean addTape(Tape Tape) {
        List<Tape> TapeList;
        try {
            log.debug("addTape[1]: ");
            if (Tape == null) {
                throw new Exception("Adding Tape error, Tape equals null");
            }
            TapeList = loadBeanXML(Constants.TAPE_XML_PATH);
            if (TapeList == null){
                TapeList = new ArrayList<>();
            }
            log.debug(TapeList);
            TapeList.add(Tape);
            writeXML(TapeList);

            log.debug("Tape added[2]: ");
            return true;
        } catch (Exception e) {
            log.error("addTape Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public boolean updateTape(Tape Tape, long id) {
        List<Tape> TapeList;
        try {
            log.debug("updateTape[1]");
            TapeList = loadBeanXML(Constants.TAPE_XML_PATH);
            log.debug("Loaded list in upd is[2]: " + TapeList);
            Tape TapeBean = TapeList.stream()
                    .filter(bean -> bean.getMeasurementId() == id)
                    .findFirst()
                    .get();
            int index = TapeList.indexOf(TapeBean);
            TapeList.set(index, Tape);
            writeXML(TapeList);
            log.trace("Updating complete[3]");
            return true;
        } catch (Exception e) {
            log.error("updateTape Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public boolean deleteTapeById(long id) {
        List<Tape> TapeList;
        try {
            log.debug("deleteTapeById[1]:");
            TapeList = loadBeanXML(Constants.TAPE_XML_PATH);
            Predicate<Tape> isDeletable = Tape -> Tape.getMeasurementId() == id;

            if (TapeList.removeIf(isDeletable)) {
                log.trace("Saving");
                if (TapeList.isEmpty()) {
                    FileWriter file = new FileWriter(ConfigurationUtil.getConfigurationEntry(Constants.TAPE_XML_PATH));
                    file.write("");
                    file.close();
                } else
                    writeXML(TapeList);
                log.debug("Tape deleted[2]:");
                return true;
            }
            else {
                log.debug("No record with stated id");
                return false;
            }
        }
        catch (Exception e) {
            log.error("deleteTapeById Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public Optional<ThreeSiteSkinFold> getThreeSiteSkinFoldById(long id) {
        ThreeSiteSkinFold ThreeSiteSkinFoldBean;
        List<ThreeSiteSkinFold> ThreeSiteSkinFoldList;
        try {
            log.debug("getThreeSiteSkinFoldID[1]");
            ThreeSiteSkinFoldList = loadBeanXML(Constants.THREESITESKINFOLD_XML_PATH);
            ThreeSiteSkinFoldBean = ThreeSiteSkinFoldList.stream()
                    .filter(bean -> bean.getMeasurementId() == id)
                    .findFirst()
                    .get();
            log.trace("Receiving record complete");
            return Optional.of(ThreeSiteSkinFoldBean);
        }
        catch(Exception e) {
            log.error("Unable to receive ThreeSiteSkinFold with id = " + id);
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    public boolean addThreeSiteSkinFold(ThreeSiteSkinFold ThreeSiteSkinFold) {
        List<ThreeSiteSkinFold> ThreeSiteSkinFoldList;
        try {
            log.debug("addThreeSiteSkinFold[1]: ");
            if (ThreeSiteSkinFold == null) {
                throw new Exception("Adding ThreeSiteSkinFold error, ThreeSiteSkinFold equals null");
            }
            ThreeSiteSkinFoldList = loadBeanXML(Constants.THREESITESKINFOLD_XML_PATH);
            if (ThreeSiteSkinFoldList == null){
                ThreeSiteSkinFoldList = new ArrayList<>();
            }
            log.debug(ThreeSiteSkinFoldList);
            ThreeSiteSkinFoldList.add(ThreeSiteSkinFold);
            writeXML(ThreeSiteSkinFoldList);

            log.debug("ThreeSiteSkinFold added[2]: ");
            return true;
        } catch (Exception e) {
            log.error("addThreeSiteSkinFold Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public boolean updateThreeSiteSkinFold(ThreeSiteSkinFold ThreeSiteSkinFold, long id) {
        List<ThreeSiteSkinFold> ThreeSiteSkinFoldList;
        try {
            log.debug("updateThreeSiteSkinFold[1]");
            ThreeSiteSkinFoldList = loadBeanXML(Constants.THREESITESKINFOLD_XML_PATH);
            log.debug("Loaded list in upd is[2]: " + ThreeSiteSkinFoldList);
            ThreeSiteSkinFold ThreeSiteSkinFoldBean = ThreeSiteSkinFoldList.stream()
                    .filter(bean -> bean.getMeasurementId() == id)
                    .findFirst()
                    .get();
            int index = ThreeSiteSkinFoldList.indexOf(ThreeSiteSkinFoldBean);
            ThreeSiteSkinFoldList.set(index, ThreeSiteSkinFold);
            writeXML(ThreeSiteSkinFoldList);
            log.trace("Updating complete[3]");
            return true;
        } catch (Exception e) {
            log.error("updateThreeSiteSkinFold Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public boolean deleteThreeSiteSkinFoldById(long id) {
        List<ThreeSiteSkinFold> ThreeSiteSkinFoldList;
        try {
            log.debug("deleteThreeSiteSkinFoldById[1]:");
            ThreeSiteSkinFoldList = loadBeanXML(Constants.THREESITESKINFOLD_XML_PATH);
            Predicate<ThreeSiteSkinFold> isDeletable = ThreeSiteSkinFold -> ThreeSiteSkinFold.getMeasurementId() == id;

            if (ThreeSiteSkinFoldList.removeIf(isDeletable)) {
                log.trace("Saving");
                if (ThreeSiteSkinFoldList.isEmpty()) {
                    FileWriter file = new FileWriter(ConfigurationUtil.getConfigurationEntry(Constants.THREESITESKINFOLD_XML_PATH));
                    file.write("");
                    file.close();
                } else
                    writeXML(ThreeSiteSkinFoldList);
                log.debug("ThreeSiteSkinFold deleted[2]:");
                return true;
            }
            else {
                log.debug("No record with stated id");
                return false;
            }
        }
        catch (Exception e) {
            log.error("deleteThreeSiteSkinFoldById Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public Optional<FourSiteSkinFold> getFourSiteSkinFoldById(long id) {
        FourSiteSkinFold FourSiteSkinFoldBean;
        List<FourSiteSkinFold> FourSiteSkinFoldList;
        try {
            log.debug("getFourSiteSkinFoldID[1]");
            FourSiteSkinFoldList = loadBeanXML(Constants.FOURSITESKINFOLD_XML_PATH);
            FourSiteSkinFoldBean = FourSiteSkinFoldList.stream()
                    .filter(bean -> bean.getMeasurementId() == id)
                    .findFirst()
                    .get();
            log.trace("Receiving record complete");
            return Optional.of(FourSiteSkinFoldBean);
        }
        catch(Exception e) {
            log.error("Unable to receive FourSiteSkinFold with id = " + id);
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    public boolean addFourSiteSkinFold(FourSiteSkinFold FourSiteSkinFold) {
        List<FourSiteSkinFold> FourSiteSkinFoldList;
        try {
            log.debug("addFourSiteSkinFold[1]: ");
            if (FourSiteSkinFold == null) {
                throw new Exception("Adding FourSiteSkinFold error, FourSiteSkinFold equals null");
            }
            FourSiteSkinFoldList = loadBeanXML(Constants.FOURSITESKINFOLD_XML_PATH);
            if (FourSiteSkinFoldList == null){
                FourSiteSkinFoldList = new ArrayList<>();
            }
            log.debug(FourSiteSkinFoldList);
            FourSiteSkinFoldList.add(FourSiteSkinFold);
            writeXML(FourSiteSkinFoldList);

            log.debug("FourSiteSkinFold added[2]: ");
            return true;
        } catch (Exception e) {
            log.error("addFourSiteSkinFold Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public boolean updateFourSiteSkinFold(FourSiteSkinFold FourSiteSkinFold, long id) {
        List<FourSiteSkinFold> FourSiteSkinFoldList;
        try {
            log.debug("updateFourSiteSkinFold[1]");
            FourSiteSkinFoldList = loadBeanXML(Constants.FOURSITESKINFOLD_XML_PATH);
            log.debug("Loaded list in upd is[2]: " + FourSiteSkinFoldList);
            FourSiteSkinFold FourSiteSkinFoldBean = FourSiteSkinFoldList.stream()
                    .filter(bean -> bean.getMeasurementId() == id)
                    .findFirst()
                    .get();
            int index = FourSiteSkinFoldList.indexOf(FourSiteSkinFoldBean);
            FourSiteSkinFoldList.set(index, FourSiteSkinFold);
            writeXML(FourSiteSkinFoldList);
            log.trace("Updating complete[3]");
            return true;
        } catch (Exception e) {
            log.error("updateFourSiteSkinFold Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public boolean deleteFourSiteSkinFoldById(long id) {
        List<FourSiteSkinFold> FourSiteSkinFoldList;
        try {
            log.debug("deleteFourSiteSkinFoldById[1]:");
            FourSiteSkinFoldList = loadBeanXML(Constants.FOURSITESKINFOLD_XML_PATH);
            Predicate<FourSiteSkinFold> isDeletable = FourSiteSkinFold -> FourSiteSkinFold.getMeasurementId() == id;

            if (FourSiteSkinFoldList.removeIf(isDeletable)) {
                log.trace("Saving");
                if (FourSiteSkinFoldList.isEmpty()) {
                    FileWriter file = new FileWriter(ConfigurationUtil.getConfigurationEntry(Constants.FOURSITESKINFOLD_XML_PATH));
                    file.write("");
                    file.close();
                } else
                    writeXML(FourSiteSkinFoldList);
                log.debug("FourSiteSkinFold deleted[2]:");
                return true;
            }
            else {
                log.debug("No record with stated id");
                return false;
            }
        }
        catch (Exception e) {
            log.error("deleteFourSiteSkinFoldById Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public Optional<List<User>> getAllUsers(){
        try {
            log.debug("getAllUsers[1]:");
            List<User> UsersList = loadBeanXML(Constants.USER_XML_PATH);
            log.debug("Users received[2]: ");
            return Optional.of(UsersList);
        }
        catch(Exception e){
            log.error("showAllUsers Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<List<Result>> getAllResults(){
        try {
            log.debug("getAllResults[1]:");
            List<Result> ResultsList = loadBeanXML(Constants.RESULT_XML_PATH);
            log.debug("Results received[2]: ");
            return Optional.of(ResultsList);
        }
        catch(Exception e){
            log.error("showAllResults Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<List<Tape>> getAllTapes(){
        try {
            log.debug("getAllTapes[1]:");
            List<Tape> TapesList = loadBeanXML(Constants.TAPE_XML_PATH);
            log.debug("Tapes received[2]: ");
            return Optional.of(TapesList);
        }
        catch(Exception e){
            log.error("showAllTapes Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<List<ThreeSiteSkinFold>> getAllThreeSiteSkinFolds(){
        try {
            log.debug("getAllThreeSiteSkinFolds[1]:");
            List<ThreeSiteSkinFold> ThreeSiteSkinFoldsList = loadBeanXML(Constants.THREESITESKINFOLD_XML_PATH);
            log.debug("ThreeSiteSkinFolds received[2]: ");
            return Optional.of(ThreeSiteSkinFoldsList);
        }
        catch(Exception e){
            log.error("showAllThreeSiteSkinFolds Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<List<FourSiteSkinFold>> getAllFourSiteSkinFolds(){
        try {
            log.debug("getAllFourSiteSkinFolds[1]:");
            List<FourSiteSkinFold> FourSiteSkinFoldsList = loadBeanXML(Constants.FOURSITESKINFOLD_XML_PATH);
            log.debug("FourSiteSkinFolds received[2]: ");
            return Optional.of(FourSiteSkinFoldsList);
        }
        catch(Exception e){
            log.error("showAllFourSiteSkinFolds Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    public boolean deleteAll() {
        try {
            FileWriter file = new FileWriter(ConfigurationUtil.getConfigurationEntry(Constants.USER_XML_PATH));
            file.write("");
            file.close();
            file = new FileWriter(ConfigurationUtil.getConfigurationEntry(Constants.RESULT_XML_PATH));
            file.write("");
            file.close();
            file = new FileWriter(ConfigurationUtil.getConfigurationEntry(Constants.TAPE_XML_PATH));
            file.write("");
            file.close();
            file = new FileWriter(ConfigurationUtil.getConfigurationEntry(Constants.THREESITESKINFOLD_XML_PATH));
            file.write("");
            file.close();
            file = new FileWriter(ConfigurationUtil.getConfigurationEntry(Constants.FOURSITESKINFOLD_XML_PATH));
            file.write("");
            file.close();
            return true;
        } catch (Exception e) {
            log.error("deleteAll Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
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
                        double fatPercentage, Stage stage){
        log.debug("Start getInfo");
        switch (stage){
            case UNDERWEIGHT :
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
                return(Stage.HEAlTHY);
            else if (fatPercentage >= 19 && fatPercentage < 25)
                return(Stage.OVERWEIGHT);
            else if (fatPercentage >= 25 && fatPercentage < 30)
                return(Stage.OBESE);
            else
                return(Stage.EXTREMELY_OBESE);
        } else {
            if (fatPercentage < 21)
                return (Stage.UNDERWEIGHT);
            else if (fatPercentage >= 21 && fatPercentage < 33)
                return(Stage.HEAlTHY);
            else if (fatPercentage >= 33 && fatPercentage < 39)
                return(Stage.OVERWEIGHT);
            else if (fatPercentage >= 39 && fatPercentage < 44)
                return(Stage.OBESE);
            else
                return(Stage.EXTREMELY_OBESE);
        }
    }


    public double getFatPercentage(String measurement, String gender, double height, int age,
                                   double a, double b, double c, double d) throws Exception {
        log.debug("Start getFatPercentage");
        double fatPercentage, bodyDensity;
        try{
            if(measurement.equals("Tape")) {
                if (gender.equals("m")) {
                    if (a == 0 || b == 0 || height == 0)
                        throw new Exception("required characteristics are not entered! Gender: m, Measurement: Tape");
                    fatPercentage = 86.01 * log10(a - b) - 70.041 * log10(height) + 36.76;
                } else {
                    if (a == 0 || b == 0 || c == 0 || height == 0)
                        throw new Exception("required characteristics are not entered! Gender: f, Measurement: Tape");
                    fatPercentage = 163.205 * log10(b + c - a) - 97.684 * log10(height) - 78.387;
                }
            }
            else if(measurement.equals("ThreeSiteSkinFold")) {
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
            }
            else {
                if (gender.equals("m")) {
                    if (a == 0 || b == 0 || c == 0 || d == 0 || age == 0)
                        throw new Exception("required characteristics are not entered! Gender: m, Measurement: FourSiteSkinFold");
                    bodyDensity = (0.29288 * (a+b+c+d)) - (0.0005 * ((a+b+c+d) * (a+b+c+d))) + (0.15845 * age) - 5.76377;
                } else {
                    if (a == 0 || b == 0 || c == 0 || d == 0 || age == 0)
                        throw new Exception("required characteristics are not entered! Gender: f, Measurement: FourSiteSkinFold");
                    bodyDensity = (0.29669 * (a+b+c+d)) - (0.00043 * ((a+b+c+d) * (a+b+c+d))) + (0.2963 * age) + 1.4072;
                }
                fatPercentage = (495 / bodyDensity) - 450;
            }
            return fatPercentage;
        } catch(Exception e){
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
            }
            else{
                throw new Exception("gender is incorrect");
            }
        }
        catch(Exception e){
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return new Measurement(gender);
        }
    }

    public List<Result> getAllResultsById(long id) {
        List<Result> ResultList;
        List<Result> ResultListById;
        try {
            log.info("getAllResultId[1]");
            ResultList = loadBeanXML(Constants.RESULT_XML_PATH);
            ResultListById = (ResultList.stream()
                    .filter(bean -> bean.getUserid() == id)
                    .toList());
            log.trace("Receiving record complete" + ResultListById);
            return ResultListById;
        } catch (Exception e) {
            log.error("Unable to receive Result with id = " + id);
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public boolean analysis(long userid) throws ParseException {
        log.debug("Start analysis");
        List<Result> ResultList = getAllResultsById(userid);
        List <Date> ResultDate = new ArrayList<>();
        try {
            ResultList.forEach((res) -> {
                try {
                    ResultDate.add(ResultUtil.changeStringDate(res.getDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
            log.info("ResultDate" + ResultDate);
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
            List<Result> ResultList = getAllResultsById(userid);
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
        List<Result> ResultList = getAllResultsById(userid);
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
