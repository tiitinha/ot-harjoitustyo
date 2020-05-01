/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.dao;

import static junit.framework.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import recipebook.domain.DatabaseService;
import recipebook.domain.FakeUserDao;
import recipebook.domain.Ingredient;
import recipebook.domain.Recipe;
import recipebook.domain.User;

/**
 *
 * @author tiitinha
 */
public class RecipeDaoTest {

    private DatabaseService db;
    private String path;
    private RecipeDao recipeDao;
    private UserDao userDao;
    private User user;

    
    @Before
    public void setUp() {
        db = new DatabaseService();
        path = "./src/test/resources/database";
        db.createDatabase(path);
        userDao = new FakeUserDao();
        recipeDao = new DatabaseRecipeDao(path, userDao);
        
        user = new User("Testi", "salasana");
        userDao.createUser(user);
    }
    
    @Test
    public void addingARecipeSuccessfullyReturnsTrue() {
        boolean value = recipeDao.addRecipe("test", "Testi");
        assertTrue(value);
    }
    
    @Test
    public void addingIngredientToARecipeAddsIngredient() {
        recipeDao.addRecipe("omlette", user.getName());
        Ingredient ingredient = new Ingredient("egg", 1, "pcs");
        recipeDao.addIngredient(ingredient, "Testi");
        
        Recipe recipe = recipeDao.fetchRecipe("omlette");
        assertTrue(recipe.getIngredients().containsKey("egg"));
    }

}
