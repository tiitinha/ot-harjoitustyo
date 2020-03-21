/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 * The class represents a single ingredient that is used in a recipe. Each ingredient is associated with a name, amount and unit of of the amount.
 * @author tiitinha
 */
public class Ingredient {
    
    private String name;
    private int amount;
    private String unit;

    
    /**
     * 
     * @param name name of the ingredient
     * @param amount amount of the said ingredient
     * @param unit unit of the amount
     */
    public Ingredient(String name, int amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }    

    /**
     * 
     * @return a string representation of the ingredient, form is name: amount unit
     */
    @Override
    public String toString() {
        return name + ": " + amount + " " + unit;
    }
    
    
}
