package Handelsakademin.StoreProgram;

public class Customer extends User {
    private String streetName;
    private int zipCode;
    private String city;
    private static int nextId = 0;

    public Customer(String firstName, String lastName, String email, boolean working, String streetName, int zipCode, String city, String password) {
        super(nextId, firstName, lastName, email, working, password);
        nextId++;
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.city = city;
    }
}
