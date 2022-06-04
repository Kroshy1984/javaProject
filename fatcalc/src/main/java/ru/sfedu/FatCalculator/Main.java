package ru.sfedu.FatCalculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import ru.sfedu.FatCalculator.dataprovider.DataProviderCsv;
import ru.sfedu.FatCalculator.dataprovider.DataProviderH2;
import ru.sfedu.FatCalculator.dataprovider.DataProviderXml;
import ru.sfedu.FatCalculator.dataprovider.IDataProvider;
import ru.sfedu.FatCalculator.model.*;
import ru.sfedu.FatCalculator.utils.CreateUtil;
import ru.sfedu.FatCalculator.utils.HibernateUtil;

import java.util.List;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        SessionFactory factory = HibernateUtil.getSessionFactory();

        IDataProvider dataProvider = setDataProvider(args[0]);
        setOperation(args, dataProvider);
    }

    private static IDataProvider setDataProvider(String arg) throws Exception {
        IDataProvider dataProvider = new DataProviderCsv();
        try {
            switch (arg) {
                case "CSV" -> dataProvider = new DataProviderCsv();
                case "XML" -> dataProvider = new DataProviderXml();
                case "H2" -> dataProvider = new DataProviderH2();
                default -> throw new Exception("invalid data type");
            }
            log.debug("Set {} data provider.", dataProvider.getClass().getSimpleName());
            return dataProvider;
        } catch (Exception e) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    private static void setOperation(String[] args, IDataProvider dataProvider) throws Exception {

        if (args[1].equals(Constants.ADD_USER)) {
            log.info("addUser: ".concat(args[2]));
            User user = CreateUtil.createUser(Long.parseLong(args[2]), args[3], args[4]);
            dataProvider.addUser(user);
        }

        if (args[1].equals(Constants.DELETE_USER)) {
            log.info("deleteUserById: ".concat(args[2]));
            dataProvider.deleteUserById(Long.parseLong(args[2]));
        }

        if (args[1].equals(Constants.GET_USER)) {
            log.info("getUserById: ".concat(args[2]));
            User user = dataProvider.getUserById(Long.parseLong(args[2])).get();
            log.info(user);
        }

        if (args[1].equals(Constants.GET_ALL_USERS)) {
            log.info("getAllUsers: ");
            List<User> userList = dataProvider.getAllUsers().get();
            userList.forEach(log::info);
        }

        if (args[1].equals(Constants.UPDATE_USER)) {
            log.info("updateUser: ");
            User user = CreateUtil.createUser(Long.parseLong(args[3]), args[4], args[5]);
            dataProvider.updateUser(user, Long.parseLong(args[2]));
        }

        if (args[1].equals(Constants.ADD_RESULT)) {
            log.info("addResult: ".concat(args[2]));
            Result result = CreateUtil.createResult(Long.parseLong(args[3]), Double.parseDouble(args[4]), args[5], Stage.valueOf(args[6]));
            dataProvider.addResult(result);
        }

        if (args[1].equals(Constants.DELETE_RESULT)) {
            log.info("deleteResultById: ".concat(args[2]));
            dataProvider.deleteResultById(Long.parseLong(args[2]));
        }

        if (args[1].equals(Constants.GET_RESULT)) {
            log.info("getResultById: ".concat(args[2]));
            Result result = dataProvider.getResultById(Long.parseLong(args[2])).get();
            log.info(result);
        }

        if (args[1].equals(Constants.GET_ALL_RESULTS)) {
            log.info("getAllResults: ");
            List<Result> resultList = dataProvider.getAllResults().get();
            resultList.forEach(log::info);
        }

        if (args[1].equals(Constants.UPDATE_RESULT)) {
            log.info("updateResult: ");
            Result result = CreateUtil.createResult(Long.parseLong(args[3]), Double.parseDouble(args[4]), args[5], Stage.valueOf(args[6]));
            dataProvider.updateResult(result, Long.parseLong(args[2]));
        }

        if (args[1].equals(Constants.ADD_TAPE)) {
            log.info("addTape: ".concat(args[2]));
            Tape tape = CreateUtil.createTape(Long.parseLong(args[3]), args[4], Integer.parseInt(args[5]),
                    Double.parseDouble(args[6]), Double.parseDouble(args[7]), Double.parseDouble(args[8]),
                    Double.parseDouble(args[9]), Double.parseDouble(args[10]), Double.parseDouble(args[11]));
            dataProvider.addTape(tape);
        }

        if (args[1].equals(Constants.DELETE_TAPE)) {
            log.info("deleteTapeById: ".concat(args[2]));
            dataProvider.deleteTapeById(Long.parseLong(args[2]));
        }

        if (args[1].equals(Constants.GET_TAPE)) {
            log.info("getTapeById: ".concat(args[2]));
            Tape tape = dataProvider.getTapeById(Long.parseLong(args[2])).get();
            log.info(tape);
        }

        if (args[1].equals(Constants.GET_ALL_TAPES)) {
            log.info("getAllTapes: ");
            List<Tape> tapeList = dataProvider.getAllTapes().get();
            tapeList.forEach(log::info);
        }

        if (args[1].equals(Constants.UPDATE_TAPE)) {
            log.info("updateTape: ");
            Tape tape = CreateUtil.createTape(Long.parseLong(args[3]), args[4], Integer.parseInt(args[5]),
                    Double.parseDouble(args[6]), Double.parseDouble(args[7]), Double.parseDouble(args[8]),
                    Double.parseDouble(args[9]), Double.parseDouble(args[10]), Double.parseDouble(args[11]));
            dataProvider.updateTape(tape, Long.parseLong(args[2]));
        }

        if (args[1].equals(Constants.ADD_THREESITESKINFOLD)) {
            log.info("addThreeSiteSkinFold: ".concat(args[2]));
            ThreeSiteSkinFold threeSiteSkinFold = CreateUtil.createThreeSiteSkinFold(Long.parseLong(args[3]), args[4], Integer.parseInt(args[5]),
                    Double.parseDouble(args[6]), Double.parseDouble(args[7]), Double.parseDouble(args[8]), Double.parseDouble(args[9]),
                    Double.parseDouble(args[10]), Double.parseDouble(args[11]), Double.parseDouble(args[12]));
            dataProvider.addThreeSiteSkinFold(threeSiteSkinFold);
        }

        if (args[1].equals(Constants.DELETE_THREESITESKINFOLD)) {
            log.info("deleteThreeSiteSkinFoldById: ".concat(args[2]));
            dataProvider.deleteThreeSiteSkinFoldById(Long.parseLong(args[2]));
        }

        if (args[1].equals(Constants.GET_THREESITESKINFOLD)) {
            log.info("getThreeSiteSkinFoldById: ".concat(args[2]));
            ThreeSiteSkinFold threeSiteSkinFold = dataProvider.getThreeSiteSkinFoldById(Long.parseLong(args[2])).get();
            log.info(threeSiteSkinFold);
        }

        if (args[1].equals(Constants.GET_ALL_THREESITESKINFOLDS)) {
            log.info("getAllThreeSiteSkinFolds: ");
            List<ThreeSiteSkinFold> threeSiteSkinFoldList = dataProvider.getAllThreeSiteSkinFolds().get();
            threeSiteSkinFoldList.forEach(log::info);
        }

        if (args[1].equals(Constants.UPDATE_THREESITESKINFOLD)) {
            log.info("updateThreeSiteSkinFold: ");
            ThreeSiteSkinFold threeSiteSkinFold = CreateUtil.createThreeSiteSkinFold(Long.parseLong(args[3]), args[4], Integer.parseInt(args[5]),
                    Double.parseDouble(args[6]), Double.parseDouble(args[7]), Double.parseDouble(args[8]), Double.parseDouble(args[9]),
                    Double.parseDouble(args[10]), Double.parseDouble(args[11]), Double.parseDouble(args[12]));
            dataProvider.updateThreeSiteSkinFold(threeSiteSkinFold, Long.parseLong(args[2]));
        }

        if (args[1].equals(Constants.ADD_FOURSITESKINFOLD)) {
            log.info("addFourSiteSkinFold: ".concat(args[2]));
            FourSiteSkinFold fourSiteSkinFold = CreateUtil.createFourSiteSkinFold(Long.parseLong(args[3]), args[4], Integer.parseInt(args[5]),
                    Double.parseDouble(args[6]), Double.parseDouble(args[7]), Double.parseDouble(args[8]), Double.parseDouble(args[9]),
                    Double.parseDouble(args[10]), Double.parseDouble(args[11]));
            dataProvider.addFourSiteSkinFold(fourSiteSkinFold);
        }

        if (args[1].equals(Constants.DELETE_FOURSITESKINFOLD)) {
            log.info("deleteFourSiteSkinFoldById: ".concat(args[2]));
            dataProvider.deleteFourSiteSkinFoldById(Long.parseLong(args[2]));
        }

        if (args[1].equals(Constants.GET_FOURSITESKINFOLD)) {
            log.info("getFourSiteSkinFoldById: ".concat(args[2]));
            FourSiteSkinFold fourSiteSkinFold = dataProvider.getFourSiteSkinFoldById(Long.parseLong(args[2])).get();
            log.info(fourSiteSkinFold);
        }

        if (args[1].equals(Constants.GET_ALL_FOURSITESKINFOLDS)) {
            log.info("getAllFourSiteSkinFolds: ");
            List<FourSiteSkinFold> fourSiteSkinFoldList = dataProvider.getAllFourSiteSkinFolds().get();
            fourSiteSkinFoldList.forEach(log::info);
        }

        if (args[1].equals(Constants.UPDATE_FOURSITESKINFOLD)) {
            log.info("updateFourSiteSkinFold: ");
            FourSiteSkinFold fourSiteSkinFold = CreateUtil.createFourSiteSkinFold(Long.parseLong(args[3]), args[4], Integer.parseInt(args[5]),
                    Double.parseDouble(args[6]), Double.parseDouble(args[7]), Double.parseDouble(args[8]), Double.parseDouble(args[9]),
                    Double.parseDouble(args[10]), Double.parseDouble(args[11]));
            dataProvider.updateFourSiteSkinFold(fourSiteSkinFold, Long.parseLong(args[2]));
        }

        if (args[1].equals(Constants.MEASURE_FAT)) {
            log.info("measureFat: ");
            dataProvider.measureFat(args[2], Long.parseLong(args[3]), args[4], args[5], Double.parseDouble(args[6]),
                    Double.parseDouble(args[7]), Integer.parseInt(args[8]), args[9], Double.parseDouble(args[10]),
                    Double.parseDouble(args[11]), Double.parseDouble(args[12]), Double.parseDouble(args[13]),
                    Boolean.parseBoolean(args[14]));
        }

        if (args[1].equals(Constants.ANALYSIS)) {
            log.info("measureFat: ");
            if (args.length == 3)
                dataProvider.analysis(Long.parseLong(args[2]));
            else if (args.length == 5)
                dataProvider.analysis(Long.parseLong(args[2]), args[3], args[4]);
            else
                throw new Exception("incorrect number of parameters");
        }
    }
}
    
