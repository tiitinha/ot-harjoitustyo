/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.domain;

import java.util.List;
import recipebook.dao.RecipeDao;
import recipebook.dao.UserDao;

/**
 *
 * @author tiitinha
 */
public class RecipebookService {

    private RecipeDao recipeDao;
    private UserDao userDao;
    private User loggedIn;

    public RecipebookService(RecipeDao recipeDao, UserDao userDao) {
        this.recipeDao = recipeDao;
        this.userDao = userDao;
    }

    /**
     * Creates a new recipe by calling RecipeDao-method.
     * @param name the name of the new recipe
     * @param user the user who is adding the recipe
     * @return true, if creating the recipe succeeds, otherwise false
     */
    public boolean createNewRecipe(String name, String user) {
        try {
            recipeDao.addRecipe(name, user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Adds a new ingredient to the recipe with the given name by calling RecipeDao-method.
     * @param recipeName the name of the recipe to which the ingredient is added
     * @param name the name of the ingredient
     * @param amount the amount of the ingredient
     * @param unit the unit of the ingredient
     * @return true, if adding successful, otherwise false
     */
    public boolean addIngredient(String recipeName, String name, int amount, String unit) {
        try {
            Recipe recipe = fetchRecipe(recipeName);
            Ingredient ingredient = new Ingredient(name, amount, unit);
            recipe.addIngredient(ingredient);
            recipeDao.addIngredient(ingredient, recipeName);
            return true;
        } catch (Exception e) {
            return false;
        }
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
     * Method that assigns the user to be logged in, if the password and username are correct.
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
     * Creates a new user by calling UserDao-method.
     * @param username username for the new user
     * @param password password for the new user
     * @return returns false, if creating the user fails, otherwise true
     */
    public boolean createUser(String username, String password) {
            User user = new User(username, password);
            return userDao.createUser(user);
    }

    public Recipe fetchRecipe(String name) {
        return recipeDao.fetchRecipe(name);
    }

    public List<Recipe> getUsersRecipes(String username) {
        return recipeDao.getUsersRecipes(username);
    }

}
