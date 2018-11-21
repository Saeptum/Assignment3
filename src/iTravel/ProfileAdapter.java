package iTravel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ProfileAdapter {
    private Connection conn;

    public ProfileAdapter(Connection conn, boolean reset) throws SQLException{
        this.conn = conn;
        if (reset) {
            Statement stmt = conn.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                stmt.execute("DROP TABLE Profile");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of Matches
                stmt.execute("CREATE TABLE Profile ("
                        + "FirstName CHAR(50),"
                        + "LastName CHAR(50),"
                        + "Address CHAR(50),"
                        + "Position1 INT"
                        + ")");
            }
        }
    }
    // Get all Flight Numbers
    public ObservableList<String> getProfileFirstNames() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = conn.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM Profile";

        // Store all flightdata into a resultset
        rs = stmt.executeQuery(sqlStatement);

        // loop for all the rs rows and add flightNumber to list
        while (rs.next()) {
            list.add(rs.getString("FirstName"));
        }
        return list;
    }
    public void addProfile(Profile profile) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Profile (FirstName, LastName, Address, Position1) " + "VALUES ('" + profile.getFirstName() + "','" + profile.getLastName() + "','" + profile.getAddress() + "'," + profile.getPosition() + ")");
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }
    public void removeProfile(String firstName) {
        try {
            Statement stmt = conn.createStatement();
            // Query and delete
            String sql = "DELETE FROM Profile WHERE FirstName=?";

            // Use prepared statements for dynamic sql strings
            PreparedStatement ps = conn.prepareStatement(sql);
            // Replace ? with FlightNumber
            ps.setString(1, firstName);
            // Then execute the update
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
    public void removeProfile(Profile profile) {
        try {
            Statement stmt = conn.createStatement();
            // Query and delete
            String sql = "DELETE FROM Profile WHERE FirstName=? AND LastName=? AND Address=?";

            // Use prepared statements for dynamic sql strings
            PreparedStatement ps = conn.prepareStatement(sql);
            // Replace ? with FlightNumber
            ps.setString(1, profile.getFirstName());
            ps.setString(2, profile.getLastName());
            ps.setString(3, profile.getAddress());
            // Then execute the update
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }

    public ObservableList<Profile> getProfilesList() throws SQLException {
        ObservableList<Profile> profilesList = FXCollections.observableArrayList();
        Statement stmt = conn.createStatement();
        ResultSet profileList = stmt.executeQuery("SELECT * FROM Profile");
        while (profileList.next()) {
            // public Matches(int num, String home, String visitor, int hScore, int vScore)
            profilesList.add(new Profile(profileList.getString("FirstName"), profileList.getString("LastName"), profileList.getString("Address"), profileList.getInt("Position1")));
        }
        return profilesList;
    }
}
