/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.UI;

import java.io.FileInputStream;
import java.util.Properties;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import recipebook.Dao.DatabaseRecipeDao;
import recipebook.Dao.DatabaseUserDao;
import recipebook.Dao.RecipeDao;
import recipebook.Dao.UserDao;
import recipebook.Domain.RecipeBookService;

/**
 *
 * @author tiitinha
 */
public class GUI extends Application {

    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));

        String database = properties.getProperty("database");
        String databasePath = properties.getProperty("databasePath");
        
        String databaseFile = database + databasePath;
        
        UserDao userDao = new DatabaseUserDao(databaseFile);
        RecipeDao recipeDao = new DatabaseRecipeDao(databaseFile, userDao);
        RecipeBookService recipebook = new RecipeBookService(recipeDao, userDao);
        recipebook.checkIfDatabaseExists(databaseFile);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        StackPane root = new StackPane();
        Text text = new Text("Recipebook is currently under construction!");
        root.getChildren().add(text);
        
        Scene scene = new Scene(root, 1200, 800);
        
        primaryStage.setScene(scene);
        
        primaryStage.setTitle("Recipebook");
        primaryStage.show();
    }
    
    @Override
    public void stop() {
        System.out.println("The application is now closed. Thank you for using the recipebook.");
    }

}
