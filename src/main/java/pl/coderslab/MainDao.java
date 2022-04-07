package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.util.Arrays;

public class MainDao {
    public static void main(String[] args) {
        User user = new User();
        user.setEmail("Laura@yahoo.com");
        user.setUserName("Laurencja");
        user.setPassword("haslo123");

        User user1 = new User();
        user1.setEmail("Dominikos@yahoo.com");
        user1.setUserName("Dominikos");
        user1.setPassword("qwerty123");

        User user2 = new User();
        user2.setEmail("Kamilla@yahoo.com");
        user2.setUserName("Kamilla");
        user2.setPassword("12345");

        UserDao userDao = new UserDao();
//        userDao.create(user);
//        userDao.create(user1);
//        userDao.create(user2);
        System.out.println(Arrays.toString(userDao.findAll()));

        //userDao.update(new User(2, "Dominikos@yahoo.com", "Domino", "qwerty123"));

        //userDao.delete(1);
        //System.out.println(Arrays.toString(userDao.findAll()));

        System.out.println(userDao.read(1));
    }
}
