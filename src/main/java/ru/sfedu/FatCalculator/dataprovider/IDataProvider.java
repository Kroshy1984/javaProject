package ru.sfedu.FatCalculator.dataprovider;

import ru.sfedu.FatCalculator.model.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface IDataProvider {

    public Optional<Result> getResultById(long id);
    public boolean addResult(Result Result);
    public boolean updateResult(Result Result, long id);
    public boolean deleteResultById(long id);

    public Optional<User> getUserById(long id);
    public boolean addUser(User user);
    public boolean updateUser(User User, long id);
    public boolean deleteUserById(long id);

    public Optional<ThreeSiteSkinFold> getThreeSiteSkinFoldById(long id);
    public boolean addThreeSiteSkinFold(ThreeSiteSkinFold ThreeSiteSkinFold);
    public boolean updateThreeSiteSkinFold(ThreeSiteSkinFold ThreeSiteSkinFold, long id);
    public boolean deleteThreeSiteSkinFoldById(long id);

    public Optional<FourSiteSkinFold> getFourSiteSkinFoldById(long id);
    public boolean addFourSiteSkinFold(FourSiteSkinFold FourSiteSkinFold);
    public boolean updateFourSiteSkinFold(FourSiteSkinFold FourSiteSkinFold, long id);
    public boolean deleteFourSiteSkinFoldById(long id);

    public Optional<Tape> getTapeById(long id);
    public boolean addTape(Tape Tape);
    public boolean updateTape(Tape Tape, long id);
    public boolean deleteTapeById(long id);

    public Optional<List<User>> getAllUsers();
    public Optional<List<Result>> getAllResults();
    public Optional<List<Tape>> getAllTapes();
    public Optional<List<ThreeSiteSkinFold>> getAllThreeSiteSkinFolds();
    public Optional<List<FourSiteSkinFold>> getAllFourSiteSkinFolds();

    public boolean measureFat(String measurement, long userid, String name, String surname, double weight, double height,
                              int age, String gender, double a, double b, double c, double d, boolean info) throws Exception;

    public boolean analysis(long userid) throws ParseException;

    public boolean analysis(long userid, String date1, String date2) throws ParseException;
}
