/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.dao;

import java.util.List;
import recipebook.domain.Ingredient;
import recipebook.domain.Recipe;
import recipebook.domain.User;

/**
 *
 * @author tiitinha
 */
public interface RecipeDao {
    
    boolean addRecipe(String name, String author);
    
    boolean addIngredient(Ingredient ingredient, String recipeName);
    
    Recipe fetchRecipe(String name);
    
    List<Recipe> getAll();
    
    List<Recipe> getUsersRecipes(String username);
    
}
