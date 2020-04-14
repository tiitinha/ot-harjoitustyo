/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.Dao;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
        return recipes.stream().filter(r -> r.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<Recipe> getAll() {
        return recipes;
    }

    @Override
    public List<Recipe> getUsersRecipes(User user) {
        return recipes.stream().filter(r -> r.getAuthor().equals(user)).collect(Collectors.toList());
    }
    
}
