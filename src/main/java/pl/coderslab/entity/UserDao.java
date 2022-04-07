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

    private static final String READ_USER_QUERY =
            "SELECT * FROM users WHERE id = ?";

    private static final String FIND_ALL_USER_QUERY =
            "SELECT * FROM users";

    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET email = ?, username = ?, password = ?" +
                    "WHERE id = ?";

    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id=?";


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

    //    public User read(int userId) {
//        User user = new User();
//
//        try (Connection conn = DbUtil.getConnection()) {
//            PreparedStatement preparedStatement = conn.prepareStatement(READ_USER_QUERY);
//            preparedStatement.setInt(1,userId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            System.out.println(resultSet.getInt(1,));
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//    public User read(int userId) {
//        String[] columnNames = {"id","email","username","password"};
//        User user = new User();
//        try (Connection conn = DbUtil.getConnection()) {
//            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
//            statement.setInt(1,userId);
//            ResultSet resultSet = statement.executeQuery(); {
//                while (resultSet.next()) {
//                    user.setPassword(resultSet.getString());
//                    for (String columnName : columnNames) {
//                        System.out.println(resultSet.getString(columnName));
//
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return new User();
//    }

    public User read(int userId) {

        User user = new User();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            {
                if (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setEmail(resultSet.getString("email"));
                    user.setUserName(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_USER_QUERY);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, hashPassword(user.getPassword()));
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_USER_QUERY);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User[] findAll() {
        User[] users = new User[0];
        try (Connection conn = DbUtil.getConnection()) {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(FIND_ALL_USER_QUERY)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String email = resultSet.getString("email");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    User user = new User(id, username, email, password);
                    users = addToArray(user, users);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1); // Tworzymy kopię tablicy powiększoną o 1.
        tmpUsers[users.length] = u; // Dodajemy obiekt na ostatniej pozycji.
        return tmpUsers; // Zwracamy nową tablicę.
    }


}
