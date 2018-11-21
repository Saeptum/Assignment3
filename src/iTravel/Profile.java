package iTravel;

public class Profile {
    private String firstName;
    private String lastName;
    private String address;
    private int position;

    public Profile(String firstName, String lastName, String address, int position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.position = position;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
