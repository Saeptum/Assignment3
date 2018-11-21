package iTravel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RemoveFlightController implements Initializable {
    @FXML
    private ComboBox<Integer> flightsData;
    private FlightsAdapter flightsAdapter;
    @FXML
    private Button remove = new Button("remove");
    final ObservableList<Integer> data = FXCollections.observableArrayList();
    // Append datalist of flightNumbers to combobox
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        flightsData.setItems(data);
    }
    // private int method to parse textfield strings to integer
    private int pInt(TextField str) {
        if (str != null) {
            return Integer.parseInt(str.getText());
        } else {
            return 0;
        }
    }
    // Append flightNumbers list to data
    public void buildComboBox() throws SQLException {
        data.addAll(flightsAdapter.getFlightNumbers());
    }
    // Set the flight adapter to whatever it's defined to in main controller
    public void setFlightsAdapter(FlightsAdapter flightsAdapter) throws SQLException {
        this.flightsAdapter = flightsAdapter;
        buildComboBox();
    }

    @FXML
    public void remove() {
        // Get the value of selected combobox if it's not null
        if (flightsData.getValue() != null) {
            flightsAdapter.removeFlight(flightsData.getValue());
        } else {
            System.out.println("Warning, combobox has not been selected");
        }
        Stage stage = (Stage) remove.getScene().getWindow();
        stage.close();

    }


}
