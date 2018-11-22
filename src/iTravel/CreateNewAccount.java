package iTravel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateNewAccount {
    private AccountAdapter accountAdapter;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private Label userID;
    @FXML
    private Button add;
    private Account account;
    public void setAccountAdapter(AccountAdapter accountAdapter) {
        this.accountAdapter = accountAdapter;
        userID.setText(String.valueOf((new Account()).getUserID()));
    }

    public void setLabel(String text) {
        userID.setText(text);
    }
    @FXML
    public void addAccount() {
        accountAdapter.addAccount(new Account(firstName.getText(), lastName.getText(), phoneNumber.getText()));
        Stage stage = (Stage) add.getScene().getWindow();
        stage.close();
    }

    public void setFirstName(String firstName) {
        this.firstName.setText(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.setText(lastName);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.setText(phoneNumber);
    }
}
