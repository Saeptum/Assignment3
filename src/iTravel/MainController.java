/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iTravel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Abdelkader
 */
public class MainController implements Initializable {

    @FXML
    private MenuBar mainMenu;
    private Connection conn;

    public void showAbout() throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        Parent About = (Parent) fxmlLoader.load();

        Scene scene = new Scene(About);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iTravel/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    public void exit() {

        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }

    public void showAddFlight(Parent parent) throws IOException {
        Scene scene = new Scene(parent);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/TennisBallGames/WesternLogo.png"));
        stage.setTitle("Add Flight");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    private FlightsAdapter flightsAdapter;
    public void flightListener() {
        /*// Toggle the comments below after you finish the requirement of Task #3

        // create Teams model
        teams = new TeamsAdapter(conn, false);
        // create matches model
        matches = new MatchesAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddMatchController.fxml"));
        Parent addMatch = (Parent) fxmlLoader.load();
        AddMatchController addMatchController = (AddMatchController) fxmlLoader.getController();
        addMatchController.setModel(matches, teams);

        Scene scene = new Scene(addMatch);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/TennisBallGames/WesternLogo.png"));
        stage.setTitle("Add New Match");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();*/
        try {
            // Create new flightsAdapter
            flightsAdapter = new FlightsAdapter(conn, false);
            // Instantiate an AddFlightController object, set its private flightsAdapter to this controller's flightsAdapter
            // THIS IS NULL RIGHT NOW
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddFlight.fxml"));
            // Load FXMLloader so there's no nullpointer exceptions
            Parent addFlight = (Parent) fxmlLoader.load();
            AddFlightController addFlightController = (AddFlightController) fxmlLoader.getController();
            addFlightController.setFlightsAdapter(flightsAdapter);
            showAddFlight(addFlight);
        } catch (IOException | SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }

    public void RemovingFlight(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("RemoveFlight.fxml"));
        primaryStage.setTitle("Removing FLight");
        primaryStage.setScene(new Scene(root, 480, 400));
        primaryStage.show();
    }

    public void showRemoveFlight() throws Exception {
        Stage stage = new Stage();
        RemovingFlight(stage);
    }


    public void RemoveFlightListener() throws Exception {
        showRemoveFlight();
    }

    public void AddProfile(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("AddProfile.fxml"));
        primaryStage.setTitle("Add Profile");
        primaryStage.setScene(new Scene(root, 480, 400));
        primaryStage.show();
    }

    public void showAddProfile() throws Exception {
        Stage stage = new Stage();
        AddProfile(stage);
    }


    public void AddProfileListener() throws Exception {
        showAddProfile();
    }
    @FXML
    public void resetDB() {
        try {
            // create flightsAdapter model
            flightsAdapter = new FlightsAdapter(conn, true);
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // Create a named constant for the URL
            // NOTE: This value is specific for Java DB
            String DB_URL = "jdbc:derby:TeamDB;create=true";
            // Create a connection to the database
            conn = DriverManager.getConnection(DB_URL);

        } catch (SQLException ex) {

        }

    }
}

