package iTravel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AccountAdapter {
    Connection connection;
    public AccountAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = conn.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                stmt.execute("DROP TABLE UserAccount");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of Matches
                stmt.execute("CREATE TABLE UserAccount ("
                        + "FirstName CHAR(50),"
                        + "LastName CHAR(50),"
                        + "PhoneNumber CHAR(50),"
                        + "UserID INT"
                        + ")");
            }
        }
    }
    public ObservableList<Account> getAccountList() throws SQLException {
        ObservableList<Account> accountsList = FXCollections.observableArrayList();
        Statement stmt = connection.createStatement();
        ResultSet accountList = stmt.executeQuery("SELECT * FROM UserAccount");
        while (accountList.next()) {
            // Call this constructor instead of the one with required params so userID doesn't unintentionally increase
            // Instead use account's setter to not mess with static int variables
            Account account = new Account();
            String firstName = accountList.getString("FirstName");
            String lastName = accountList.getString("LastName");
            String phoneNumber = accountList.getString("PhoneNumber");
            int userID = accountList.getInt("UserID");
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setPhoneNumber(phoneNumber);
            account.setUserID(userID);
            // public Matches(int num, String home, String visitor, int hScore, int vScore)
            accountsList.add(account);
        }
        return accountsList;
    }
    public void addAccount(Account account) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO UserAccount (FirstName, LastName, PhoneNumber, UserID) " + "VALUES ('" + account.getFirstName() + "','" + account.getLastName() + "','" + account.getPhoneNumber() + "'," + account.getUserID() + ")");
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }
    public void removeAccount(Account account) {
        try {
            Statement stmt = connection.createStatement();
            // Query and delete
            String sql = "DELETE FROM UserAccount WHERE UserID=?";

            // Use prepared statements for dynamic sql strings
            PreparedStatement ps = connection.prepareStatement(sql);
            // Replace ? with FlightNumber
            ps.setInt(1, account.getUserID());
            // Then execute the update
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
    public int getUserID() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet accountList = stmt.executeQuery("SELECT * FROM UserAccount");
        int userId = 0;
        while(accountList.next()) {
            System.out.println(accountList.getInt("UserID"));
            userId = accountList.getInt("UserID");
        }
        return userId;

    }
}
