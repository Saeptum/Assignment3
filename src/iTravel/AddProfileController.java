package iTravel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddProfileController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField address;
    @FXML
    private RadioButton businessManager;
    @FXML
    private RadioButton lineOfBusinessExecutive;
    @FXML
    private RadioButton networkAdministrator;
    @FXML
    private Button button;
    private ProfileAdapter profileAdapter;

    public RadioButton getBusinessManager() {
        return businessManager;
    }

    public RadioButton getLineOfBusinessExecutive() {
        return lineOfBusinessExecutive;
    }

    public RadioButton getNetworkAdministrator() {
        return networkAdministrator;
    }

    public String firstName() {
        return firstName.getText();
    }

    public void setFirstName(String firstNameParam) {
        firstName.setText(firstNameParam);
    }
    public void setLastName(String lastNameParam) {
        lastName.setText(lastNameParam);
    }
    public void setAddress(String addressParam) {
        address.setText(addressParam);
    }

    public String lastName() {
        return lastName.getText();
    }

    public String address() {
        return address.getText();
    }
    // Fire a specified radioButton
    public void setRadioButtonSelected(RadioButton radioButton) {
        radioButton.fire();
    }
    public int getRadioButtonSelected() {
        int index = 0;
        boolean isSelected[] = {businessManager.isSelected(), lineOfBusinessExecutive.isSelected(), networkAdministrator.isSelected()};
        for (int i = 0; i < 3; i++) {
            if (isSelected[i]) {
                index = i;
            }
        }
        return index;
    }

    public void setProfileAdapter(ProfileAdapter profileAdapter) {
        this.profileAdapter = profileAdapter;
    }
    @FXML
    public void addProfile() {
        Profile profile = new Profile(firstName(), lastName(), address(), getRadioButtonSelected());
        profileAdapter.addProfile(profile);

        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

}
