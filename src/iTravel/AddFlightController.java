package iTravel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
public class AddFlightController implements Initializable {
    private String label;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    // FXML declarations
    @FXML
    private TextField flightNumber;
    @FXML
    private TextField businessSeatCapacity;
    @FXML
    private TextField businessFare;
    @FXML
    private TextField date;
    @FXML
    private TextField timeOfDeparture;
    @FXML
    private TextField origin;
    @FXML
    private TextField executiveSeatCapacity;
    @FXML
    private TextField executiveFare;
    @FXML
    private TextField timeOfArrival;
    @FXML
    private TextField destination;
    @FXML
    private Button add = new Button("add");
    private Flights flight;
    private FlightsAdapter flightsAdapter;
    private Connection connection;


    // private int method to parse textfield strings to integer
    private int pInt(TextField str) {
        if (str != null) {
            return Integer.parseInt(str.getText());
        } else {
            return 0;
        }
    }

    public void setFlightsAdapter(FlightsAdapter flightsAdapter) {
        this.flightsAdapter = flightsAdapter;
    }

    @FXML
    public void addFlight() {
        flight = new Flights(pInt(flightNumber), pInt(businessSeatCapacity), pInt(businessFare), date.getText(), pInt(timeOfDeparture), origin.getText(), pInt(executiveSeatCapacity), pInt(executiveFare), pInt(timeOfArrival), destination.getText());
        flightsAdapter.addFlight(flight);
        Stage stage = (Stage) add.getScene().getWindow();
        stage.close();
    }
}
