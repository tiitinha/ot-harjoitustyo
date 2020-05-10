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
 * Service to handle the app logic for the recipebook.
 *
 * @author tiitinha
 */
public class RecipebookService {

    private RecipeDao recipeDao;
    private UserDao userDao;
    private User loggedIn;

    /**
     * Sets the recipeDao and userDao to be used when handling data.
     *
     * @param recipeDao recipeDao-instance, which handles the data-saving and
     * loading of recipes
     * @param userDao userDao-instance, which handles the data-saving and
     * loading of users
     */
    public RecipebookService(RecipeDao recipeDao, UserDao userDao) {
        this.recipeDao = recipeDao;
        this.userDao = userDao;
    }

    /**
     * Creates a new recipe by calling RecipeDao-method.
     *
     * @param name the name of the new recipe
     * @param user the user who is adding the recipe
     * @return true, if creating the recipe succeeds, otherwise false
     */
    public boolean createNewRecipe(String name, String user) {
        return recipeDao.addRecipe(name, user);

    }

    /**
     * Adds a new ingredient to the recipe with the given name by calling
     * RecipeDao-method.
     *
     * @param recipeName the name of the recipe to which the ingredient is added
     * @param name the name of the ingredient
     * @param amount the amount of the ingredient
     * @param unit the unit of the ingredient
     * @return true, if adding successful, otherwise false
     */
    public boolean addIngredient(String recipeName, String name, int amount, String unit) {
        Recipe recipe = fetchRecipe(recipeName);
        Ingredient ingredient = new Ingredient(name, amount, unit);
        recipe.addIngredient(ingredient);
        return recipeDao.addIngredient(ingredient, recipeName);

    }

    /**
     * Checks whether a database to store the data exists, if not, then creates
     * a new database.
     *
     * @param path the path of the database file from the config file
     * @return returns true, if connection successful, otherwise false
     */
    public boolean connectToDatabase(String path) {

        DatabaseService database = new DatabaseService();

        database.createDatabase(path);

        return recipeDao.fetchAllRecipes();

    }

    /**
     * Method that assigns the user to be logged in, if the password and
     * username are correct.
     *
     * @param username username of the user to be logged in
     * @param password password of the user to be logged in
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

    /**
     * Method that sets logged user to null.
     */
    public void logout() {
        loggedIn = null;
    }

    public User getLoggedUser() {
        return loggedIn;
    }

    /**
     * Creates a new user by calling UserDao-method.
     *
     * @param username username for the new user
     * @param password password for the new user
     * @return returns false, if creating the user fails, otherwise true
     */
    public boolean createUser(String username, String password) {
        User user = new User(username, password);
        return userDao.createUser(user);
    }

    /**
     * Returns a recipe with the name.
     *
     * @param name the name of the recipe to be fetched
     * @return A recipe object with the name equal to the given parameter
     */
    public Recipe fetchRecipe(String name) {
        return recipeDao.fetchRecipe(name);
    }

    /**
     * Returns a list of recipes with a given username as author.
     *
     * @param username the username of the user whose recipes are searched
     * @return A list of recipes
     */
    public List<Recipe> getUsersRecipes(String username) {
        return recipeDao.getUsersRecipes(username);
    }

}
