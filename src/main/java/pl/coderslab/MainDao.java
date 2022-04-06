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

        UserDao userDao = new UserDao();
        //userDao.create(user1);
        System.out.println(Arrays.toString(userDao.findAll()));
    }
}
