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

    public DatabaseRecipeDao(String database, UserDao users) throws Exception {
        this.database = database;
        this.users = users;
    }

    @Override
    public Recipe addRecipe(User user, String name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Recipe fetchRecipe(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Recipe> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
