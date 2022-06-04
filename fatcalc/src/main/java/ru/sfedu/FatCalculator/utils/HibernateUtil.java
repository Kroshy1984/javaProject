package ru.sfedu.FatCalculator.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;;
import org.hibernate.cfg.Configuration;
import ru.sfedu.FatCalculator.Main;

import java.io.File;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static final Logger log = LogManager.getLogger(Main.class);

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration;
                String CFG_PATH = System.getProperty("path");
                if(System.getProperty("path") == null)
                    configuration = new Configuration().configure();
                else configuration = new Configuration().configure(new File(CFG_PATH));
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                log.error("getSessionFactory Error");
                log.error(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        return sessionFactory;
    }

}
