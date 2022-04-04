package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.utils.DbUtil;


import java.sql.*;
import java.util.Arrays;

public class UserDao {
    //zapytanie które doda nam użytkownika do bazy
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) " +
                    "VALUES (?, ?, ?)";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {

            //to jest element z zadania ========================================
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);

            //=======================================
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            //To jest element z zadania ============================================
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            //==================================================================
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // public User read(int userId) {}

    public void update(User user) {
    }

    public void delete(int userId) {
    }

    //findAll metoda zwraca tablice obiektów jeżeli takowy istnieje

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1); // Tworzymy kopię tablicy powiększoną o 1.
        tmpUsers[users.length] = u; // Dodajemy obiekt na ostatniej pozycji.
        return tmpUsers; // Zwracamy nową tablicę.
    }


}
