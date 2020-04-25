/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.domain;

import java.sql.SQLException;
import recipebook.dao.RecipeDao;
import recipebook.dao.UserDao;

/**
 *
 * @author tiitinha
 */
public class RecipeBookService {

    private RecipeDao recipeDao;
    private UserDao userDao;
    private User loggedIn;

    public RecipeBookService(RecipeDao recipeDao, UserDao userDao) {
        this.recipeDao = recipeDao;
        this.userDao = userDao;
    }

    /**
     *
     * @param name the name of the new recipe
     * @param user the user who is adding the recipe
     * @return true, if creating the recipe succeeds, otherwise false
     */
    public Recipe createNewRecipe(String name, String user) {
        Recipe recipe = new Recipe(name, user);
        return recipe;
    }

    /**
     *
     * @param recipe the recipe to which the ingredient is added
     * @param name the name of the ingredient
     * @param amount the amount of the ingredient
     * @param unit the unit of the ingredient
     */
    public void addIngredient(Recipe recipe, String name, int amount, String unit) {
        Ingredient ingredient = new Ingredient(name, amount, unit);
        recipe.addIngredient(ingredient);
    }

    /**
     * Checks whether a database to store the data exists, if not, then creates
     * a new database
     *
     * @param path the path of the database file from the config file
     * @return returns true, if connection successful, otherwise false
     */
    public boolean connectToDatabase(String path) {

        DatabaseService database = new DatabaseService();

        database.createDatabase(path);

        try {
            userDao.fetchUsers();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     *
     * @param username username
     * @param password password
     * @return true, if the username exists in the database, false otherwise
     */
    public boolean login(String username, String password) {
        User user = userDao.findByUserName(username);

        if (user == null) {
            return false;
        }

        if (user.checkPassword(password)) {
            loggedIn = user;
            return true;
        }

        return false;
    }

    public void logout() {
        loggedIn = null;
    }

    public User getLoggedUser() {
        return loggedIn;
    }

    /**
     * 
     * @param username username for the new user
     * @param password password for the new user
     * @return returns false, if creating the user fails, otherwise true
     */
    public boolean createUser(String username, String password) {
        try {
            User user = new User(username, password);
            User returnUser = userDao.createUser(user);
            if (returnUser != null) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

}
