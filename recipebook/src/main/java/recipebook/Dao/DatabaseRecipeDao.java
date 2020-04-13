/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.Dao;
import java.util.ArrayList;
import java.util.List;
import recipebook.Domain.Recipe;
import recipebook.Domain.User;
;

/**
 *
 * @author tiitinha
 */
public class DatabaseRecipeDao implements RecipeDao {
    private String database;
    private UserDao users;
    private List<Recipe> recipes;

    public DatabaseRecipeDao(String database, UserDao users) throws Exception {
        this.database = database;
        this.users = users;
    }

    @Override
    public boolean addRecipe(User user, String name) throws Exception {
        return false;
    }

    @Override
    public Recipe fetchRecipe(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Recipe> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Recipe> getUsersRecipes(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
