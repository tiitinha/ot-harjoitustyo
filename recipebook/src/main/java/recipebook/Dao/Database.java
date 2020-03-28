/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tiitinha
 */
public class Database {

    /**
     * 
     * @param name the name of the database file
     * @return false if exception during creating the database, otherwise true
     */
    public boolean createDatabase(String name) {
        Connection connection = null;
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:src/Main/Resources/" + name + ".db");
            Statement s = db.createStatement();
            s.execute("CREATE TABLE User (id INTEGER PRIMARY KEY, name TEXT, password TEXT)");
            s.execute("CREATE TABLE Recipe (id INTEGER PRIMARY KEY, name TEXT, createUserId INTEGER)");
            s.execute("CREATE TABLE Ingredient (id INTEGER PRIMARY KEY, recipeId INTEGER, name TEXT, amount INTEGER, unit TEXT)");

        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                return false;
            }
        }
        return true;
    }

}
