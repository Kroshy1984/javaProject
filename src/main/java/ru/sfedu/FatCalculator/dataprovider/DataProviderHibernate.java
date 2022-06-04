package ru.sfedu.FatCalculator.dataprovider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sfedu.FatCalculator.Constants;
import ru.sfedu.FatCalculator.utils.HibernateUtil;

import java.util.List;

public class DataProviderHibernate {
    private static final Logger log = LogManager.getLogger(DataProviderCsv.class);

    public List getSize() {
        log.debug("getSize start");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            return session.createNativeQuery(Constants.GET_SIZE_DB).getResultList();
        } catch (Exception e) {
            log.error("getSize Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public List getTables() {
        log.debug("getTables start");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            return session.createNativeQuery(Constants.GET_TABLES_DB).getResultList();
        } catch (Exception e) {
            log.error("getTables Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public List getUsers() {
        log.debug("getUsers start");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            return session.createNativeQuery(Constants.GET_USERS_DB).getResultList();
        } catch (Exception e) {
            log.error("getUsers Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public List getColumns() {
        log.debug("getColumns start");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            return session.createNativeQuery(Constants.GET_COLUMNS_DB).getResultList();
        } catch (Exception e) {
            log.error("getColumns Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
}
