package project.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import project.beans.Pair;
import project.beans.User;
import project.utils.Constants;
import project.utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class DefaultController {
    private static Logger log = LogManager.getLogger(HibernateUtil.class.getName());

    public User addUser(User user) {
        try {
            log.info("addUser[1]{}");
            Session session = new HibernateUtil().getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            log.info("addUser[2]{}");
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("addUser[3]{} ".concat(e.getMessage()));
        }
        return user;
    }

    public Optional<User> getUser(Long id) {
        Optional<User> user = Optional.empty();
        try {
            log.info("getUser[1]{}");
            Session session = new HibernateUtil().getSessionFactory().openSession();
            session.beginTransaction();
            user = Optional.of(((User) session.get(User.class, id)));
            log.info("getUser[2]{}");
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("getUser [3]{} ".concat(e.getMessage()));
        }
        return user;
    }

    public User updateUser(User user) {
        try {
            log.info("updateUser[1]{}");
            Session session = new HibernateUtil().getSessionFactory().openSession();
            session.beginTransaction();
            session.update(user);
            log.info("updateUser[2]{}");
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("updateUser[3]{} ".concat(e.getMessage()));
        }
        return user;
    }

    public User deleteUser(User user){
        try {
            log.info("deleteUser[1]{}");
            Session session = new HibernateUtil().getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(user);
            log.info("deleteUser[2]{}");
            session.getTransaction().commit();
        }catch (Exception e){
            log.error("deleteUser[3]{} ".concat(e.getMessage()));
        }
        return user;
    }

    public Optional<List<User>> getAllUsers() {
        Optional<List<User>> usersList = Optional.empty();
        try {
            log.info("getAllUsers[1]{}");
            Session session = new HibernateUtil().getSessionFactory().openSession();
            session.beginTransaction();
            usersList = Optional.of(session.createQuery(Constants.SELECT_ALL_FROM_USERS, User.class).getResultList());
            log.info("getAllUsers[2]{}");
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("getAllUsers[3]{} ".concat(e.getMessage()));
        }
        return usersList;
    }

    public void createPair(Pair pair){
        try {
            log.info("createPair[1]{}");
            Session session = new HibernateUtil().getSessionFactory().openSession();
            session.beginTransaction();
            session.save(pair);
            log.info("createPair[2]{}");
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("createPair[3]{} ".concat(e.getMessage()));
        }
    }


}
