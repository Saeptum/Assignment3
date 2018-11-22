package iTravel;

public class Account {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private static int userID = 0;

    public Account() {

    }

    public Account(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        userID++;
        System.out.println(userID);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUserID() {
        return userID;
    }
    public String getUserStringID() {
        return String.valueOf(userID);
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
}
