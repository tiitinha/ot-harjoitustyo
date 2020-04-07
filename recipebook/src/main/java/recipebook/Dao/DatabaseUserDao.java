/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import recipebook.Domain.User;

/**
 *
 * @author tiitinha
 */
public class DatabaseUserDao implements UserDao {

    private final String database;
    private final List<User> users;

    public DatabaseUserDao(String path) {
        this.database = path;

        users = new ArrayList<>();
    }

    /**
     * Gets all users from database and adds to an ArrayList
     *
     * @return returns true, if connection to database successful, otherwise
     * false
     * @throws java.lang.Exception if other than SQLException, the exception is
     * thrown
     */
    public boolean getUsersFromDatabase() throws Exception {
        try {
            try (Connection db = DriverManager.getConnection("jdbc:h2:" + database, "admin", "")) {
                PreparedStatement stmt = db.prepareStatement("SELECT * FROM User;");
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    User user = new User(rs.getString("name"), rs.getString("password"));
                    users.add(user);
                }

                rs.close();
                stmt.close();
                db.close();
            }

        } catch (SQLException e) {
            return false;
        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    /**
     *
     * @param user an instance of User class
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public User createUser(User user) throws SQLException {
        if (!users.contains(user)) {
            try {
                Connection db = DriverManager.getConnection("jdbc:h2:" + database, "admin", "");
                Statement s = db.createStatement();

                PreparedStatement stmt = db.prepareStatement("INSERT INTO User (name, password) VALUES (?, ?);");
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getPassword());

                stmt.executeUpdate();
                stmt.close();
                db.close();

                users.add(user);

            } catch (SQLException e) {
                System.out.println(e.fillInStackTrace());
                throw e;
            }
            return user;
        }
        return null;
    }

    @Override
    public User findByUserName(String username) {
        return users.stream()
                .filter(u -> u.getName()
                .equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

}
