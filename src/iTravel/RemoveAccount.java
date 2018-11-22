package iTravel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class RemoveAccount {
    private AccountAdapter accountAdapter;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button remove;
    private ObservableList<String> comboBoxData = FXCollections.observableArrayList();

    public void setAccountAdapter(AccountAdapter accountAdapter) {
        this.accountAdapter = accountAdapter;
        try {
            ObservableList<Account> accountsList = accountAdapter.getAccountList();
            accountsList.forEach((account) -> {
                System.out.print(account.getUserStringID());
                comboBoxData.add(account.getFirstName() + " " + account.getLastName());
            });
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        comboBox.setItems(comboBoxData);
    }

    @FXML
    public void removeAccount() throws SQLException {
        if (comboBox.getValue() != null) {
            // Access profiles.getList() at index of combobox
            // Remove profile by certain profile info
            accountAdapter.removeAccount(accountAdapter.getAccountList().get(comboBox.getSelectionModel().getSelectedIndex()));
        } else {
            System.out.println("warning, combobox has not been selected");
        }
        Stage stage = (Stage) remove.getScene().getWindow();
        stage.close();
    }
}
