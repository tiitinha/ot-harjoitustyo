/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.Domain;

import java.util.HashMap;

/**
 *
 * @author tiitinha
 */
public class Recipe implements Comparable<Recipe> {

    private String name;
    private HashMap<String, Ingredient> ingredients;
    private String author;

    public Recipe(String name, String author) {
        this.name = name;
        this.ingredients = new HashMap<>();
        this.author = author;
    }

    /**
     * Method is used to add a new ingredient to a recipe.
     *
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

    @Override
    public int compareTo(Recipe t) {
        return this.name.hashCode() - t.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Recipe)) {
            return false;
        }

        Recipe other = (Recipe) obj;

        return name.equals(other.name);
    }

    public String getAuthor() {
        return author;
    }

    
}
