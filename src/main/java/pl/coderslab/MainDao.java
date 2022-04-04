package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

public class MainDao {
    public static void main(String[] args) {
        User user = new User();
        user.setEmail("Laura@yahoo.com");
        user.setUserName("Laurencja");
        user.setPassword("haslo123");

        UserDao userDao = new UserDao();
        userDao.create(user);
    }
}
