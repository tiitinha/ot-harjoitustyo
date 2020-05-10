/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import recipebook.domain.User;

/**
 * An implementation of UserDao interface. Handles saving and fetching user data from
 * a database. Uses a DAO approach.
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
     * {@inheritDoc}
     */
    @Override
    public boolean fetchUsers() {
        try {
            Connection db = DriverManager.getConnection("jdbc:h2:" + database, "admin", "");
            PreparedStatement stmt = db.prepareStatement("SELECT * FROM User;");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("password"));
                users.add(user);
            }

            rs.close();
            stmt.close();
            db.close();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean createUser(User user) {
        if (!userExists(user)) {
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
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findByUserName(String username) {
        return users.stream()
                .filter(u -> u.getName()
                .equals(username))
                .findFirst()
                .orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAllUsers() {
        return users;
    }
    
    /**
     * A method that checks whether the user given as a parameter already exists.
     * @param user the User-object to be checked
     * @return true, if the user exists, false otherwise
     */
    public boolean userExists(User user) {
        return users.contains(user);
    }

}
