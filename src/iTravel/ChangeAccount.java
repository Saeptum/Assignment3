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

import java.sql.SQLException;

public class ChangeAccount {
    private AccountAdapter accountAdapter;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button okay;
    @FXML
    private Label label;
    private ProfileAdapter profileAdapter;
    private ObservableList<String> comboBoxData = FXCollections.observableArrayList();

    public void setAccountAdapter(AccountAdapter accountAdapter) {
        this.accountAdapter = accountAdapter;
        try {
            ObservableList<Account> accountsList = accountAdapter.getAccountList();
            accountsList.forEach((account) -> {
                comboBoxData.add(account.getFirstName() + " " + account.getLastName());
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
        fxmlLoader.setLocation(getClass().getResource("CreateNewAccount.fxml"));
        int comboBoxSelectedIndex = comboBox.getSelectionModel().getSelectedIndex();
        Parent addAccount;
        try {
            // Set up addProfileController and display it onscreen
            addAccount = (Parent) fxmlLoader.load();
            CreateNewAccount createNewAccount = (CreateNewAccount) fxmlLoader.getController();
            createNewAccount.setAccountAdapter(accountAdapter);
            Scene scene = new Scene(addAccount);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:src/TennisBallGames/WesternLogo.png"));
            stage.setTitle("Change Account");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            // Set textfields
            Account account = accountAdapter.getAccountList().get(comboBoxSelectedIndex);
            createNewAccount.setFirstName(account.getFirstName());
            createNewAccount.setLastName(account.getLastName());
            createNewAccount.setPhoneNumber(account.getPhoneNumber());

            //
            createNewAccount.setLabel(String.valueOf(account.getUserID()));

            // Remove previous profile
            accountAdapter.removeAccount(account);

            // Disable form
            comboBox.setDisable(true);
            okay.setDisable(true);
            label.setText("Please exit this form");
        } catch (Exception ex) {
            addAccount = null;
            System.out.println(ex.getMessage());
        }
    }
}
