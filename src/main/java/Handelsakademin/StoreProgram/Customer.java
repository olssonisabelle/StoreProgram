package Handelsakademin.StoreProgram;

public class Customer extends User {
    private String streetName;
    private int zipCode;
    private String city;
    private static int nextId = 0;

    public Customer(String firstName, String lastName, String email, String streetName, int zipCode, String city, String password) {
        super(nextId, firstName, lastName, email, false, password);
        nextId++;
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.city = city;
    }

    //Constructor to use when using file handling
    public Customer(int id, String firstName, String lastName, String email, Boolean working, String password, String streetName, int zipCode, String city){
        super(id,firstName,lastName,email,working,password);
        if(id >= nextId) {
            nextId = id + 1;
        }
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.city = city;
    }
    @Override
    public String getCSV(String delimiter){ //to be able to use file handling with csv
        return id + delimiter + firstName + delimiter + lastName + delimiter + email + delimiter + working + delimiter + password + delimiter + streetName + delimiter + zipCode + delimiter + city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
