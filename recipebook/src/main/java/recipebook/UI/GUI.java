/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.UI;

import java.io.FileInputStream;
import java.util.Properties;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

    private RecipeBookService recipebook;
    private Scene loginScene;
    private Scene newUserScene;
    private Scene recipeScene;
    private Scene addRecipeScene;

    private Label menuLabel = new Label();

    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));

        String database = properties.getProperty("database");
        String databasePath = properties.getProperty("databasePath");

        String databaseFile = databasePath + database;

        UserDao userDao = new DatabaseUserDao(databaseFile);
        RecipeDao recipeDao = new DatabaseRecipeDao(databaseFile, userDao);
        recipebook = new RecipeBookService(recipeDao, userDao);
        recipebook.connectToDatabase(databaseFile);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Setup the login window
        VBox welcomePane = new VBox(20);
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("Username");
        Label passwordLabel = new Label("Password");
        Label welcomeLabel = new Label("Welcome to the Recipebook! The app is currently under construction");
        TextField usernameInput = new TextField();
        TextField usernamePassword = new TextField();

        inputPane.getChildren().addAll(loginLabel, usernameInput, passwordLabel, usernamePassword);
        welcomePane.getChildren().add(welcomeLabel);
        Label loginMessage = new Label();

        Button loginButton = new Button("Login");
        Button createButton = new Button("Create new user");

        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = usernamePassword.getText();
            menuLabel.setText(username + " logged in..");

            if (recipebook.login(username, username)) {
                loginMessage.setText("");
                primaryStage.setScene(recipeScene);
                usernameInput.setText("");
            } else {
                loginMessage.setText("Invalid username or password!");
                loginMessage.setTextFill(Color.RED);
            }
        });

        createButton.setOnAction(e -> {
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);
        });

        loginPane.getChildren().addAll(welcomePane, loginMessage, inputPane, loginButton, createButton);

        loginScene = new Scene(loginPane, 750, 500);

        // Setup for creation of a new user
        Label createNewUserLabel = new Label("The username must be at least 3 characters long and the password 5.");
        VBox newUserPane = new VBox(10);
        HBox newUsernamePane = new HBox(10);

        newUsernamePane.setPadding(new Insets(10));
        TextField newUsername = new TextField();
        Label newUsernameLabel = new Label("Username");
        newUsernameLabel.setPrefWidth(150);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsername);

        HBox newUserPasswordPane = new HBox(10);
        newUserPasswordPane.setPadding(new Insets(10));
        TextField newUserPassword = new TextField();
        Label newUserPasswordLabel = new Label("Password");
        newUserPasswordLabel.setPrefWidth(150);
        newUserPasswordPane.getChildren().addAll(newUserPasswordLabel, newUserPassword);

        Label userCreationMessage = new Label();

        Button createNewUserButton = new Button("Create a new user");

        createNewUserButton.setOnAction((ActionEvent e) -> {
            String username = newUsername.getText();
            String password = newUserPassword.getText();
            
            if (username.length() < 4) {
                userCreationMessage.setText("Username is too short!");
            }
            if (password.length() < 6) {
                userCreationMessage.setText("Password too short!");
            } else if (recipebook.creteUser(username, password)) {
                userCreationMessage.setText("");
                newUsername.setText("");
                newUserPassword.setText("");
                loginMessage.setText("New user created!");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);
            } else {
                userCreationMessage.setText("Username already in use. Username has to be unique");
                userCreationMessage.setTextFill(Color.RED);
            }
        });
        
        newUserPane.getChildren().addAll(createNewUserLabel, userCreationMessage, newUsernamePane, newUserPasswordPane, createNewUserButton);
        
        newUserScene = new Scene(newUserPane, 750, 500);

        // Setup the primary stage and scene
        primaryStage.setTitle("Recipebook");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("closing");
            System.out.println(recipebook.getLoggedUser());
            if (recipebook.getLoggedUser() != null) {
                e.consume();
            }

        });
    }

    @Override
    public void stop() {
        System.out.println("The application is now closed. Thank you for using the recipebook.");
    }

}
