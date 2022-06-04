package com.mycompany.app;



import com.mycompany.db.DBWorker;
import com.mycompany.models.Boy;
import com.mycompany.models.Girl;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Boy Boy = new Boy();
        Boy.setName("test");
        Girl girl = new Girl();
        girl.setName("girl_for_test");
        try {
            DBWorker DBWorker = com.mycompany.db.DBWorker.getInstance();
            DBWorker.createPair(Boy,girl);
               
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
