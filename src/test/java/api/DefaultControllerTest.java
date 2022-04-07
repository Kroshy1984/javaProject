package api;

import project.beans.Pair;
import project.controllers.DefaultController;
import project.beans.Gender;
import project.beans.User;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.fail;

public class DefaultControllerTest {
    @Test
    public void addUserTestSuccess() {
        try {
            System.out.println("addUserTestSuccess");
            DefaultController defaultController = new DefaultController();

            User user = new User();
            user.setAge(24);
            user.setGender(Gender.MALE);
            user.setName("Sergey");
            user.setPhoto("images/photo9321.jpeg");

            System.out.println(defaultController.addUser(user));

            assert (true);
        } catch (Exception e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void updateUserTestSuccess() {
        try {
            System.out.println("updateUserTestSuccess");
            DefaultController defaultController = new DefaultController();

            User user = defaultController.getUser((long) 5).get();

            System.out.println("id = " + user.getId() + "\n"
                    + "name = " + user.getName() + "\n"
                    + "age = " + user.getAge() + "\n"
                    + "gender = " + user.getGender());

            user = new User();
            user.setId((long) 5);
            user.setAge(22);
            user.setGender(Gender.FEMALE);
            user.setName("Elena");
            user.setPhoto("images/photo.jpeg785");

            defaultController.updateUser(user);

            System.out.println("id = " + user.getId() + "\n"
                    + "name = " + user.getName() + "\n"
                    + "age = " + user.getAge() + "\n"
                    + "gender = " + user.getGender());

            assert (true);
        } catch (Exception e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void getUserTestSuccess() {
        try {
            System.out.println("getUserTestSuccess");
            DefaultController defaultController = new DefaultController();

            if (defaultController.getUser((long) 2).isPresent()) {
                User user = defaultController.getUser((long) 2).get();
                System.out.println("id = " + user.getId() + "\n"
                        + "name = " + user.getName() + "\n"
                        + "age = " + user.getAge() + "\n"
                        + "gender = " + user.getGender());
            } else {
                System.out.println("No such element");
            }
            assert (true);
        } catch (Exception e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUserTestSuccess() {
        try {
            System.out.println("deleteUserTestSuccess");
            DefaultController defaultController = new DefaultController();

            User user = new User();
            user.setId((long) 3);

            defaultController.deleteUser(user);


            assert (true);
        } catch (Exception e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void getAllUserTestSuccess() {
        try {
            System.out.println("getAllUserTestSuccess");
            DefaultController defaultController = new DefaultController();

            if (defaultController.getAllUsers().isPresent()) {
                List<User> userList = defaultController.getAllUsers().get();
                for (User user : userList) {
                    System.out.println("\nid = " + user.getId() + "\n"
                            + "name = " + user.getName() + "\n"
                            + "age = " + user.getAge() + "\n"
                            + "gender = " + user.getGender());
                }
            } else {
                System.out.println("No such element");
            }
            assert (true);
        } catch (Exception e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void createPair() {
        try {
            System.out.println("createPair");
            DefaultController defaultController = new DefaultController();

            Pair pair = new Pair();

            Set<User> users = new HashSet<>();
            users.add(defaultController.getUser((long)1).get());
            users.add(defaultController.getUser((long)4).get());
            users.add(defaultController.getUser((long)5).get());
            pair.setUsers(users);

            defaultController.createPair(pair);

            assert (true);
        } catch (Exception e) {
            fail();
            e.printStackTrace();
        }
    }


}