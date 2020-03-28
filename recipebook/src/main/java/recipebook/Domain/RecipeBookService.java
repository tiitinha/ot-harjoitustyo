/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.Domain;

import recipebook.Dao.RecipeDao;
import recipebook.Dao.UserDao;

/**
 *
 * @author tiitinha
 */
public class RecipeBookService {

    private RecipeDao recipeDao;
    private UserDao userDao;

    public RecipeBookService(RecipeDao recipeDao, UserDao userDao) {
        this.recipeDao = recipeDao;
        this.userDao = userDao;
    }

    /**
     * 
     * @param name the name of the new recipe
     * @return true, if creating the recipe succeeds, otherwise false
     */
    public boolean createNewRecipe(String name) {
        Recipe recipe = new Recipe(name);
        
        return true;
        /*
        try {

        } catch (Exception ex) {
            return false;
        }
        return true;*/
    }

    /**
     * 
     * @param recipeName the name of the recipe to which the ingredient is added
     * @param name the name of the ingredient
     * @param amount the amount of the ingredient
     * @param unit the unit of the ingredient
     * @return false, if adding the ingredient doesn't succeed, otherwise true
     */
    public boolean addIngredient(String recipeName, String name, int amount, String unit) {

        try {
            
            return true; /*recipe.addIngredient(name, amount, unit);*/
        } catch (Exception ex) {
            return false;
        }

    }
    
    /**
     * Connects to (or creates a new) local database, where all data is stored
     * @return false, if connection fails, otherwise true
     */
    public boolean connectToDatabase() {
        
        
        return true;
    }

}
