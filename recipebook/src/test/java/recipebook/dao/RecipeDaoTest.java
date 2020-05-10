/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.dao;

import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import recipebook.domain.DatabaseService;
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

    @Before
    public void setUp() {
        db = new DatabaseService();
        path = "./src/test/resources/database";
        db.createDatabase(path);
        recipeDao = new DatabaseRecipeDao(path);
        userDao = new DatabaseUserDao(path);
        
        User user = new User("testi", "salasana");
        userDao.createUser(user);
    }

    @Test
    public void addingARecipeSuccessfullyReturnsTrue() {
        boolean value = recipeDao.addRecipe("testi", "testi");
        assertTrue(value);
    }

    @Test
    public void addingIngredientToARecipeAddsIngredient() {
        recipeDao.addRecipe("omlette", "testi");
        Ingredient ingredient = new Ingredient("egg", 1, "pcs");
        recipeDao.addIngredient(ingredient, "omlette");

        Recipe recipe = recipeDao.fetchRecipe("omlette");
        assertTrue(recipe.getIngredients().containsKey("egg"));
    }

    @Test
    public void fetchRecipeReturnsCorrectRecipe() {
        recipeDao.addRecipe("omlette", "testi");

        assertEquals(new Recipe("omlette", "testi"), recipeDao.fetchRecipe("omlette"));
    }

    @Test
    public void getUsersRecipesReturnsCorrectRecipes() {
        recipeDao.addRecipe("omlette", "testi");
        recipeDao.addRecipe("omlette2", "testi");
        recipeDao.addRecipe("omlette3", "testi");
        List<Recipe> recipes = recipeDao.getUsersRecipes("testi");
        
        assertEquals(1, recipes.stream().filter(r -> r.getName().equals("omlette")).count());
    }
    
    @Test
    public void fetchAllRecipesReturnsTrueWhenSuccessful() {
        assertTrue(recipeDao.fetchAllRecipes());
    }

}
