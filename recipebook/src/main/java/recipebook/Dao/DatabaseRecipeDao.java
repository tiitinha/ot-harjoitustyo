/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import recipebook.Domain.Recipe;
import recipebook.Domain.User;

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

    @Override
    public boolean addRecipe(Recipe recipe) throws Exception {
        try {

            Connection db = DriverManager.getConnection("jdbc:h2:" + database, "admin", "");
            Statement s = db.createStatement();

            PreparedStatement stmtUser = db.prepareStatement("SELECT IDENTITY() FROM User WHERE name = ?");
            stmtUser.setString(1, recipe.getAuthor());
            ResultSet rs = stmtUser.executeQuery();

            String userId = rs.getString("id");

            PreparedStatement stmt = db.prepareStatement("INSERT INTO Recipe (name, createUserId) VALUES (?, ?);");
            stmt.setString(1, recipe.getName());
            stmt.setString(2, userId);

            stmt.executeUpdate();
            stmt.close();
            db.close();

            recipes.add(recipe);
            return true;
        } catch (Exception e) {
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
    public List<Recipe> getUsersRecipes(User user) {
        return recipes.stream().filter(r -> r.getAuthor().equals(user)).collect(Collectors.toList());
    }

}
