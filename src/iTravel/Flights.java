package iTravel;

public class Flights {
    private int flightNum;
    private int businessSeatingCapacity;
    private int businessFare;
    private String date;
    private int timeOfDeparture;
    private String origin;
    private int executiveSeatingCapacity;
    private int executiveFare;
    private int timeOfArrival;
    private String destination;

    public Flights(int flightNum, int businessSeatingCapacity, int businessFare, String date, int timeOfDeparture, String origin, int executiveSeatingCapacity, int executiveFare, int timeOfArrival, String destination) {
        this.flightNum = flightNum;
        this.businessSeatingCapacity = businessSeatingCapacity;
        this.businessFare = businessFare;
        this.date = date;
        this.timeOfDeparture = timeOfDeparture;
        this.origin = origin;
        this.executiveSeatingCapacity = executiveSeatingCapacity;
        this.executiveFare = executiveFare;
        this.timeOfArrival = timeOfArrival;
        this.destination = destination;
    }

    public int getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(int flightNum) {
        this.flightNum = flightNum;
    }

    public int getBusinessSeatingCapacity() {
        return businessSeatingCapacity;
    }

    public void setBusinessSeatingCapacity(int businessSeatingCapacity) {
        this.businessSeatingCapacity = businessSeatingCapacity;
    }

    public int getBusinessFare() {
        return businessFare;
    }

    public void setBusinessFare(int businessFare) {
        this.businessFare = businessFare;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(int timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getExecutiveSeatingCapacity() {
        return executiveSeatingCapacity;
    }

    public void setExecutiveSeatingCapacity(int executiveSeatingCapacity) {
        this.executiveSeatingCapacity = executiveSeatingCapacity;
    }

    public int getExecutiveFare() {
        return executiveFare;
    }

    public void setExecutiveFare(int executiveFare) {
        this.executiveFare = executiveFare;
    }

    public int getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(int timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
