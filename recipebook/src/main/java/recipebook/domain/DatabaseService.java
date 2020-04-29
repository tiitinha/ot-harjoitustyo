/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tiitinha
 */
public class DatabaseService {

    /**
     * Creates a new database file and tables, if they do not exist
     * @param path the path to database file
     * @return false if exception during creating the database, otherwise true
     */
    public boolean createDatabase(String path) {
        Connection connection = null;
        try {
            Connection db = DriverManager.getConnection("jdbc:h2:" + path, "admin", "");
            Statement s = db.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS User (id IDENTITY NOT NULL PRIMARY KEY, name TEXT, password TEXT)");
            s.execute("CREATE TABLE IF NOT EXISTS Recipe (id IDENTITY NOT NULL PRIMARY KEY, name TEXT, createUserId INTEGER)");
            s.execute("CREATE TABLE IF NOT EXISTS Ingredient (id IDENTITY NOT NULL PRIMARY KEY, recipeId INTEGER, name TEXT, amount INTEGER, unit TEXT)");

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
