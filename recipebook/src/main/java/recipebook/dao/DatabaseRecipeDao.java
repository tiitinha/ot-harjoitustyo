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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
    private UserDao users;
    private List<Recipe> recipes;

    public DatabaseRecipeDao(String database, UserDao users) throws Exception {
        this.database = database;
        this.users = users;
        recipes = new ArrayList();
    }

    /**
     *
     * @param name name of the recipe
     * @param author username of the recipe author
     * @return returns false if the adding fails either due to exception or
     * recipe already exists, otherwise true
     * @throws Exception
     */
    @Override
    public boolean addRecipe(String name, String authorName) throws Exception {

        Recipe recipe = new Recipe(name, authorName);

        if (recipes.contains(recipe)) {
            return false;
        }

        try {
            Connection db = DriverManager.getConnection("jdbc:h2:" + database, "admin", "");

            PreparedStatement stmtUser = db.prepareStatement("SELECT id, name FROM User WHERE name = ?");
            stmtUser.setString(1, authorName);
            ResultSet rs = stmtUser.executeQuery();

            if (rs.next()) {
                int userId = Integer.parseInt(rs.getString(1));

                PreparedStatement stmt = db.prepareStatement("INSERT INTO Recipe (name, createUserId) VALUES (?, ?);");
                stmt.setString(1, name);
                stmt.setInt(2, userId);

                stmt.executeUpdate();
                stmt.close();
                db.close();

                recipes.add(recipe);
                return true;
            }
            return false;

        } catch (NumberFormatException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     *
     * @param ingredient the ingredient to be added to a recipe and database
     * @param recipeName the name of the recipe to which the ingredient is added
     * @return
     */
    @Override
    public boolean addIngredient(Ingredient ingredient, String recipeName) {
        if (fetchRecipe(recipeName) == null) {
            return false;
        }

        Recipe recipe = fetchRecipe(recipeName);

        try {

            Connection db = DriverManager.getConnection("jdbc:h2:" + database, "admin", "");

            PreparedStatement stmtUser = db.prepareStatement("SELECT id FROM Recipe WHERE name = ?");
            stmtUser.setString(1, recipeName);
            ResultSet rs = stmtUser.executeQuery();

            if (rs.next()) {
                int recipeId = Integer.parseInt(rs.getString(1));

                PreparedStatement stmt = db.prepareStatement("INSERT INTO Ingredient (recipeId, name, amount, unit) VALUES (?, ?, ?), ?");
                stmt.setInt(1, recipeId);
                stmt.setString(2, ingredient.getName());
                stmt.setInt(3, ingredient.getAmount());
                stmt.setString(4, ingredient.getUnit());

                stmt.executeUpdate();
                stmt.close();
                db.close();

                recipe.addIngredient(ingredient);
                return true;
            }
            return false;
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean fetchAllRecipes() {
        try {
            try (Connection db = DriverManager.getConnection("jdbc:h2:" + database, "admin", "")) {
                PreparedStatement stmt = db.prepareStatement("SELECT name, createUserId FROM Recipe;");
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    PreparedStatement stmtIngredients = db.prepareStatement("SELECT name, createUserId from Ingredient WHERE createUserId = ?");
                    stmtIngredients.setString(1, rs.getString("createUserId"));
                    Recipe recipe = new Recipe(rs.getString("name"), rs.getString("createUserId"));
                    recipes.add(recipe);
                }

                rs.close();
                stmt.close();
                db.close();
            }

        } catch (SQLException e) {
            return false;
        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    @Override
    public Recipe fetchRecipe(String name) {
        return recipes.stream().filter(r -> r.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<Recipe> getAll() {
        return recipes;
    }

    @Override
    public List<Recipe> getUsersRecipes(String username) {
        return recipes.stream().filter(r -> r.getAuthor().equals(username)).collect(Collectors.toList());
    }

}
