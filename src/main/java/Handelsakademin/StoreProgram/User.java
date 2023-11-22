package Handelsakademin.StoreProgram;

public class User {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected boolean working;
    protected String password;

    public User(int id, String firstName, String lastName, String email, boolean working, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.working = working;
        this.password = password;
    }

    public User() {
    }

    public String getCSV(){
        return id + "," + firstName + "," + lastName + "," + email + "," + working + "," + password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
