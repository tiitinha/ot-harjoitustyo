/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.dao;

import java.util.List;
import recipebook.domain.User;

/**
 * An interface for Data Access Object for saving data longer term and fetching
 * the saved data.
 *
 * @author tiitinha
 */
public interface UserDao {

    /**
     * Gets all users from database and adds them to an ArrayList.
     *
     * @return returns true, if connection to database successful, otherwise
     * false
     */
    boolean fetchUsers();

    /**
     * Creates a new user to the database table User.
     *
     * @param user an instance of User class
     * @return true, if user creation successful, otherwise false
     */
    boolean createUser(User user);

    /**
     * Finds and returns a User-object, if the user with username exist.
     *
     * @param username The username to be searched from the user database.
     * @return User object, if user exists, otherwise null
     */
    User findByUserName(String username);

    /**
     * Returns all users in the recipebook as a list object.
     *
     * @return a list of all users in the recipebook
     */
    List<User> getAllUsers();

}
