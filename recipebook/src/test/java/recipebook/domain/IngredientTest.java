/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.domain;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import recipebook.domain.Ingredient;

/**
 *
 * @author tiitinha
 */
public class IngredientTest {
    
    @Test
    public void ingredientToStringCorrectReturn() {
        Ingredient ing = new Ingredient("egg", 1, "pcs");
        assertEquals("egg: 1 pcs", ing.toString());
    }
    
}
