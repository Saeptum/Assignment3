package iTravel;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FlightsAdapter {
    Connection connection;

    public FlightsAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                stmt.execute("DROP TABLE Flights");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of Matches
                stmt.execute("CREATE TABLE Flights ("
                        + "FlightNumber INT, "
                        + "BusinessSeatCapacity INT,"
                        + "BusinessFare INT,"
                        + "Date1 CHAR(10),"
                        + "TimeOfDeparture INT,"
                        + "Origin CHAR(50),"
                        + "ExecutiveSeatingCapacity INT,"
                        + "ExecutiveFare INT,"
                        + "TimeOfArrival INT,"
                        + "Destination CHAR(50)"
                        + ")");
            }
        }
    }
    public void addFlight(Flights flight) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO Flights (FlightNumber, BusinessSeatCapacity, BusinessFare, Date1, TimeOfDeparture, Origin, ExecutiveSeatingCapacity, ExecutiveFare, TimeOfArrival, Destination) "
                    + "VALUES (" + flight.getFlightNum() + " , " + flight.getBusinessSeatingCapacity() + " , " + flight.getBusinessFare() + ", '" + flight.getDate() + "'," + flight.getTimeOfDeparture() + ",'" + flight.getOrigin() + "'," + flight.getExecutiveSeatingCapacity() + "," + flight.getExecutiveFare() + "," + flight.getTimeOfArrival() + ",'" + flight.getDestination() + "')");
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
    // Get all Flight Numbers
    public ObservableList<Integer> getFlightNumbers() throws SQLException {
        ObservableList<Integer> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM Flights";

        // Store all flightdata into a resultset
        rs = stmt.executeQuery(sqlStatement);

        // loop for all the rs rows and add flightNumber to list
        while (rs.next()) {
            list.add(rs.getInt("FlightNumber"));
        }
        return list;
    }
    public void removeFlight(int flightNumber) {
        try {
            Statement stmt = connection.createStatement();
            // Query and delete
            String sql = "DELETE FROM Flights WHERE FlightNumber=?";

            // Use prepared statements for dynamic sql strings
            PreparedStatement ps = connection.prepareStatement(sql);
            // Replace ? with FlightNumber
            ps.setInt(1, flightNumber);
            // Then execute the update
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }


}
