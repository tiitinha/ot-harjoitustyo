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
        recipeDao = new DatabaseRecipeDao(path);
    }

    @Test
    public void addingARecipeSuccessfullyReturnsTrue() {
        boolean value = recipeDao.addRecipe("test", "Testi");
        assertTrue(value);
    }

    @Test
    public void addingIngredientToARecipeAddsIngredient() {
        recipeDao.addRecipe("omlette", "test");
        Ingredient ingredient = new Ingredient("egg", 1, "pcs");
        recipeDao.addIngredient(ingredient, "omlette");

        Recipe recipe = recipeDao.fetchRecipe("omlette");
        assertTrue(recipe.getIngredients().containsKey("egg"));
    }

    @Test
    public void fetchRecipeReturnsCorrectRecipe() {
        recipeDao.addRecipe("omlette", "test");

        assertEquals(new Recipe("omlette", "test"), recipeDao.fetchRecipe("omlette"));
    }

    @Test
    public void getUsersRecipesReturnsCorrectRecipes() {
        recipeDao.addRecipe("omlette", "test");
        recipeDao.addRecipe("omlette2", "test");
        recipeDao.addRecipe("omlette3", "test");
        List<Recipe> recipes = recipeDao.getUsersRecipes("test");
        
        assertEquals(1, recipes.stream().filter(r -> r.getName().equals("omlette")).count());
    }

}
