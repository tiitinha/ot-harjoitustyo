/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.dao;

import java.util.List;
import recipebook.domain.Ingredient;
import recipebook.domain.Recipe;

/**
 * An interface for Data Access Object for saving data longer term and fetching the saved data.
 * @author tiitinha
 */
public interface RecipeDao {

    /**
     * Adds a new recipe to the database, if the recipe doesn't already exist.
     *
     * @param name name of the recipe
     * @param authorName the name of the author of the recipe
     * @return returns false if the adding fails either due to exception or
     * recipe already exists, otherwise true
     */
    boolean addRecipe(String name, String authorName);

    /**
     * Adds an ingredient for a recipe in the database.
     *
     * @param ingredient the ingredient to be added to a recipe and database
     * @param recipeName the name of the recipe to which the ingredient is added
     * @return true if the ingredient is added successfully, otherwise false
     */
    boolean addIngredient(Ingredient ingredient, String recipeName);

    /**
     * Fetches and returns a recipe with a certain name.
     *
     * @param name name of the recipe to be fetched
     * @return Recipe object, if the recipe exists, otherwise null
     */
    Recipe fetchRecipe(String name);

    /**
     * Returns a list of all recipes in the recipebook.
     *
     * @return A list of all recipes in the recipebook
     */
    List<Recipe> getAll();

    /**
     * A method to get all recipes with a certain author.
     * @param username The username of the user whose recipes are fetched.
     * @return A list of all recipes with the given user as the author
     */
    List<Recipe> getUsersRecipes(String username);

    /**
     * Fetches all recipes from the database and adds them to the object
     * variable recipes.
     *
     * @return true, if database query is successful, otherwise false
     */
    boolean fetchAllRecipes();

}
