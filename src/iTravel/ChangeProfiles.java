package iTravel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangeProfiles {
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button okay;
    @FXML
    private Label label;
    private ProfileAdapter profileAdapter;
    private ObservableList<String> comboBoxData = FXCollections.observableArrayList();

    public void setProfileAdapter(ProfileAdapter profileAdapter) {
        this.profileAdapter = profileAdapter;
        try {
            ObservableList<Profile> profilesList = profileAdapter.getProfilesList();
            profilesList.forEach((profile) -> {
                comboBoxData.add(profile.getFirstName() + " " + profile.getLastName());
            });
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        comboBox.setItems(comboBoxData);
        }
    @FXML
    public void onOkay() {
        // Instantiate an RemoveFlightController object, use setter for profileAdapter
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AddProfile.fxml"));
        int comboBoxSelectedIndex = comboBox.getSelectionModel().getSelectedIndex();
        Parent addProfile;
        try {
            // Set up addProfileController and display it onscreen
            addProfile = (Parent) fxmlLoader.load();
            AddProfileController addProfileController = (AddProfileController) fxmlLoader.getController();
            addProfileController.setProfileAdapter(profileAdapter);
            Scene scene = new Scene(addProfile);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:src/TennisBallGames/WesternLogo.png"));
            stage.setTitle("Change Profiles");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            // Set textfields
            Profile profile = profileAdapter.getProfilesList().get(comboBoxSelectedIndex);
            addProfileController.setFirstName(profile.getFirstName());
            addProfileController.setLastName(profile.getLastName());
            addProfileController.setAddress(profile.getAddress());

            // Fire radio button at profile.getPosition()
            RadioButton businessManager = addProfileController.getBusinessManager();
            RadioButton lineOfBusinessExecutive = addProfileController.getLineOfBusinessExecutive();
            RadioButton networkAdministrator = addProfileController.getNetworkAdministrator();
            RadioButton radioButtons[] = {businessManager, lineOfBusinessExecutive, networkAdministrator};
            radioButtons[profile.getPosition()].fire();

            // Remove previous profile
            profileAdapter.removeProfile(profile);

            // Disable form
            comboBox.setDisable(true);
            okay.setDisable(true);
            label.setText("Please exit this form");
        } catch (Exception ex) {
            addProfile = null;
            System.out.println(ex.getMessage());
        }
    }
}
