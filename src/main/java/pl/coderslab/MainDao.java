package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;


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
//        =============================
//        Tworzenie obiektów klasy user
//        =============================
//        userDao.create(user);
//        userDao.create(user1);
//        userDao.create(user2);
//        -----------------------------

        System.out.println("========Tablica Tabeli users==============");
        User[] users = userDao.findAll();
        for (User userEntity : users) {
            System.out.println(userEntity);
        }

//        ===========================
//        Update pola w bazie danych
//        ===========================
//        userDao.update(new User(2, "Domino", "Dominikos@yahoo.com", "qwerty123"));
//        userDao.update(new User(1,"Laurita","Laurita@yahoo.com","haslo123"));
//        ---------------------------

//        ========================================
//        Usówanie krotki bazy danych o podanym id
//        ========================================
//        userDao.delete(1);
//        ----------------------------------------

        System.out.println();
        System.out.println("========metoda read()=========");
        System.out.println(userDao.read(1));
    }
}
