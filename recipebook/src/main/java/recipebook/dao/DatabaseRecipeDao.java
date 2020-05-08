/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import recipebook.domain.Ingredient;
import recipebook.domain.Recipe;

;

/**
 *
 * @author tiitinha
 */
public class DatabaseRecipeDao implements RecipeDao {

    private String database;
    private List<Recipe> recipes;

    public DatabaseRecipeDao(String database) {
        this.database = database;
        recipes = new ArrayList();
    }

    /**
     * Adds a new recipe to the database, if the recipe doesn't already exist.
     *
     * @param name name of the recipe
     * @param authorName the name of the author of the recipe
     * @return returns false if the adding fails either due to exception or
     * recipe already exists, otherwise true
     */
    @Override
    public boolean addRecipe(String name, String authorName) {

        Recipe recipe = new Recipe(name.toLowerCase(), authorName);

        if (recipes.contains(recipe)) {
            return false;
        }

        int userId = getUserId(authorName);
        System.out.println(userId);
        if (userId > 0) {

            try {
                Connection db = DriverManager.getConnection("jdbc:h2:" + database, "admin", "");

                PreparedStatement stmt = db.prepareStatement("INSERT INTO Recipe (name, createUserId) VALUES (?, ?);");
                stmt.setString(1, name.toLowerCase());
                stmt.setInt(2, userId);

                stmt.executeUpdate();
                stmt.close();
                db.close();

                System.out.println("addid");

                recipes.add(recipe);
                return true;
            } catch (NumberFormatException | SQLException e) {
                return false;
            }
        }

        return false;
    }

    /**
     * Adds an ingredient for a recipe in the database.
     *
     * @param ingredient the ingredient to be added to a recipe and database
     * @param recipeName the name of the recipe to which the ingredient is added
     * @return
     */
    @Override
    public boolean addIngredient(Ingredient ingredient, String recipeName) {
        if (fetchRecipe(recipeName.toLowerCase()) == null) {
            return false;
        }

        Recipe recipe = fetchRecipe(recipeName.toLowerCase());

        int recipeId = getRecipeId(recipeName);
        System.out.println(recipeId);

        if (recipeId > 0) {

            try {

                Connection db = DriverManager.getConnection("jdbc:h2:" + database, "admin", "");

                PreparedStatement stmt = db.prepareStatement("INSERT INTO Ingredient (recipeId, name, amount, unit) VALUES (?, ?, ?, ?)");
                stmt.setInt(1, recipeId);
                stmt.setString(2, ingredient.getName());
                stmt.setInt(3, ingredient.getAmount());
                stmt.setString(4, ingredient.getUnit());

                stmt.executeUpdate();
                stmt.close();
                db.close();

                System.out.println("ingredient");
                recipe.addIngredient(ingredient);
                return true;

            } catch (NumberFormatException | SQLException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Fetches all recipes from the database and adds them to the object
     * variable recipes
     *
     * @return true, if database query is successful, otherwise false
     */
    @Override
    public boolean fetchAllRecipes() {
        try {
            try (Connection db = DriverManager.getConnection("jdbc:h2:" + database, "admin", "")) {
                PreparedStatement stmt = db.prepareStatement("SELECT id, name, createUserId FROM Recipe;");
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Recipe recipe = new Recipe(rs.getString("name"), rs.getString("createUserId"));
                    int recipeId = rs.getInt(1);
                    try {
                        PreparedStatement stmtIngredients = db.prepareStatement("SELECT name, amount, unit from Ingredient WHERE recipeId = ?;");
                        
                        stmtIngredients.setInt(1, recipeId);

                        ResultSet rsIngredient = stmtIngredients.executeQuery();

                        while (rsIngredient.next()) {
                            Ingredient ingredient = new Ingredient(rsIngredient.getString("name"), rsIngredient.getInt("amount"), rsIngredient.getString("unit"));
                            recipe.addIngredient(ingredient);
                        }

                    } catch (SQLException e) {
                        System.out.println("error");
                        return false;
                    }
                    recipes.add(recipe);
                }

                stmt.close();
                db.close();
            }

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    /**
     * Fetches the userId for a user from the database
     *
     * @param authorName the recipe author name
     * @return userId, if the user exists, -1 otherwise
     */
    private int getUserId(String authorName) {
        try {
            Connection db = DriverManager.getConnection("jdbc:h2:" + database, "admin", "");
            PreparedStatement stmtUser = db.prepareStatement("SELECT id, name FROM User WHERE name = ?");
            stmtUser.setString(1, authorName);
            ResultSet rs = stmtUser.executeQuery();

            if (rs.next()) {
                int userId = Integer.parseInt(rs.getString(1));
                return userId;
            }
            return -1;
        } catch (NumberFormatException | SQLException e) {
            return -1;
        }
    }

    /**
     * Fetches the recipeId for a recipe from the database
     *
     * @param recipeName the name of the recipe
     * @return recipeId, if the recipe exists, -1 otherwise
     */
    private int getRecipeId(String recipeName) {

        try {
            Connection db = DriverManager.getConnection("jdbc:h2:" + database, "admin", "");

            PreparedStatement stmtUser = db.prepareStatement("SELECT id FROM Recipe WHERE name = ?");
            stmtUser.setString(1, recipeName.toLowerCase());
            ResultSet rs = stmtUser.executeQuery();

            if (rs.next()) {
                int recipeId = Integer.parseInt(rs.getString(1));
                return recipeId;
            }
        } catch (NumberFormatException | SQLException e) {
            return -1;
        }

        return -1;
    }

    /**
     * Fetches and returns a recipe
     *
     * @param name name of the recipe to be fetched
     * @return Recipe object, if the recipe exists, otherwise null
     */
    @Override
    public Recipe fetchRecipe(String name) {
        return recipes.stream().filter(r -> r.getName().equals(name)).findFirst().orElse(null);
    }

    /**
     *
     * @return A list of all recipes in the recipebook
     */
    @Override
    public List<Recipe> getAll() {
        return recipes;
    }

    /**
     *
     * @param username
     * @return A list of all recipes with the given user as the author
     */
    @Override
    public List<Recipe> getUsersRecipes(String username) {
        return recipes.stream().filter(r -> r.getAuthor().equals(username)).collect(Collectors.toList());
    }

}
