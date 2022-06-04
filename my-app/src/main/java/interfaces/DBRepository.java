package interfaces;


import com.mycompany.models.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public interface DBRepository {
    void createBoy(Boy boy) throws SQLException, IOException;

    Optional<Boy> getBoyById(Long id) throws SQLException, IOException;

    void updateBoy(Boy boy) throws SQLException, IOException;

    void deleteBoyById(Long id) throws SQLException, IOException;

    void createGirl(Girl girl) throws SQLException, IOException;

    Optional<Girl> getGirlById(Long id) throws SQLException, IOException;

    void updateGirl(Girl girl) throws SQLException, IOException;

    void deleteGirlById(Long id) throws SQLException, IOException;
   
    void createPair(Boy boy,Girl girl) throws SQLException, IOException;
}
