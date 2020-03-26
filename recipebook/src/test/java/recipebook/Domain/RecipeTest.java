/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.Domain;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author tiitinha
 */
public class RecipeTest {

    private Recipe recipe;

    @Before
    public void setUp() {
        recipe = new Recipe("omlette");
    }

    @Test
    public void addingNewIngredientReturnsTrue() {
        assertTrue(recipe.addIngredient("egg", 1, "pcs"));
    }
    
    @Test
    public void addingDuplicateIngredientReturnsFalse() {
        recipe.addIngredient("egg", 1, "pcs");
        assertFalse(recipe.addIngredient("egg", 1, "pcs"));
    }
    
    @Test
    public void addingIgredientAddsIngredientToIngredientList() {
        recipe.addIngredient("egg", 1, "pcs");
        assertTrue(recipe.getIngredients().containsKey("egg"));
    }

}
