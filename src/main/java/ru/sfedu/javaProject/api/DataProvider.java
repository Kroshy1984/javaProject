package ru.sfedu.javaProject.api;


import ru.sfedu.javaProject.model.Boy;
import ru.sfedu.javaProject.model.Girl;
import ru.sfedu.javaProject.model.Pair;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public interface DataProvider {
    //Boy
    void createBoy(Boy boy) throws SQLException, IOException;

    Optional<Boy> getBoyById(Long id) throws SQLException, IOException;

    void updateBoy(Boy boy) throws SQLException, IOException;

    void deleteBoyById(Long id) throws SQLException, IOException;
    
    //Girl
    void createGirl(Girl girl) throws SQLException, IOException;

    Optional<Girl> getGirlById(Long id) throws SQLException, IOException;

    void updateGirl(Girl girl) throws SQLException, IOException;

    void deleteGirlById(Long id) throws SQLException, IOException;

    //Pair
    void createPair(Pair pair) throws SQLException, IOException;

    Optional<Pair> getPairById(Long id) throws SQLException, IOException;

    void updatePair(Pair pair) throws SQLException, IOException;

    void deletePairById(Long id) throws SQLException, IOException;
}