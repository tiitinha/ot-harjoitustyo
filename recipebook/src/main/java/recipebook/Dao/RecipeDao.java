/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.Dao;

import java.util.List;
import recipebook.Domain.Recipe;
import recipebook.Domain.User;

/**
 *
 * @author tiitinha
 */
public interface RecipeDao {
    
    Recipe addRecipe(User user, String name) throws Exception;
    
    Recipe fetchRecipe(String name);
    
    List<Recipe> getAll();
    
}
