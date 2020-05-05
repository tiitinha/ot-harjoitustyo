/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.domain;

import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author tiitinha
 */
public class RecipeBookServiceRecipeTest {

    private FakeUserDao userDao;
    private FakeRecipeDao recipeDao;
    private User user1;
    private RecipebookService recipebook;

    @Before
    public void setUp() {

        userDao = new FakeUserDao();
        recipeDao = new FakeRecipeDao();

        user1 = new User("Testi", "salasana");
        userDao.createUser(user1);

        recipebook = new RecipebookService(recipeDao, userDao);
    }

    @Test
    public void addingAnIngredientAddsIngredientToARecipe() {
        recipebook.createNewRecipe("omlette", "test");
        recipebook.addIngredient("omlette", "egg", 1, "pcs");
        assertTrue(recipebook.fetchRecipe("omlette").getIngredients().containsKey("egg"));
    }

    @Test
    public void getUsersRecipesReturnsAListOfCorrectSize() {
        assertTrue(recipebook.getUsersRecipes("Testi") instanceof List);
        recipebook.createNewRecipe("testi", "test");
        assertEquals(1, recipebook.getUsersRecipes("test").size());
    }

    @Test
    public void getUsersRecipesContainsAddedRecipes() {
        recipebook.createNewRecipe("omlette", "Testi");
        List<Recipe> recipes = recipebook.getUsersRecipes("Testi");
        assertTrue(recipes.contains(new Recipe("omlette", "Testi")));
    }
    
    @Test
    public void getRecipeReturnsCorrectRecipeIfExists() {
        recipebook.createNewRecipe("testi", "test");
        assertEquals(new Recipe("testi", "test"), recipebook.fetchRecipe("testi"));
    }

}
