/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.HashMap;

/**
 * 
 * @author tiitinha
 */
public class Recipe {

    private String name;
    private HashMap<String, Ingredient> ingredients;

    public Recipe(String name) {
        this.name = name;
        this.ingredients = new HashMap<>();
    }

    /**
     * Method is used to add a new ingredient to a recipe.
     * @param ingredient the name of the ingredient
     * @param amount the amount of the ingredient
     * @param unit the unit of the amount
     * @return true if the ingredient doesn't exist yet, otherwise false
     */
    public boolean addIngredient(String ingredient, int amount, String unit) {
        if (ingredients.containsKey(ingredient)) {
            return false;
        } else {
            ingredients.put(ingredient, new Ingredient(ingredient, amount, unit));
            return true;
        }
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Ingredient> getIngredients() {
        return ingredients;
    }

}
