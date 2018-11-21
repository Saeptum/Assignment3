package iTravel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RemoveProfile implements Initializable {
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button remove;
    final ObservableList<String> comboBoxData = FXCollections.observableArrayList();
    @FXML
    public void removeProfile() {
        if (comboBox.getValue() != null) {
            profileAdapter.removeProfile(comboBox.getValue());
        } else {
            System.out.println("warning, combobox has not been selected");
        }
        Stage stage = (Stage) remove.getScene().getWindow();
        stage.close();
    }

    private ProfileAdapter profileAdapter;

    public void setProfileAdapter(ProfileAdapter profileAdapter) {
        this.profileAdapter = profileAdapter;
        buildComboBoxData();
    }
    public void buildComboBoxData() {
        try {
            ObservableList<Profile> profilesList = profileAdapter.getProfilesList();
            profilesList.forEach((profile) -> {
                comboBoxData.add(profile.getFirstName() + " " + profile.getLastName());
            });
        } catch (SQLException ex) {
            //displayAlert("ERROR: " + ex.getMessage());
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(comboBoxData);
    }
}
