/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.domain;

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
}
