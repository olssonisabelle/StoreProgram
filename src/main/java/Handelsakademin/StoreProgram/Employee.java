package Handelsakademin.StoreProgram;

public class Employee extends User{
    private int salary;
    private static int nextId = 0;

    public Employee(String firstName, String lastName, String email, int salary, String password) {
        super(nextId, firstName, lastName, email, true, password);
        //TODO Vi kan inte uppdatera idn på det här sättet, alla employees komemr att få 1 som id
        //TODO IDn måste uppdateras från UserHandler för att hålla koll på vilket ID som är nästa
        //TODO Kolla på hur jag gjorde i ProductHandler
        nextId ++;
        this.salary = salary;
    }

    //Constructor to use when using file handling
    public  Employee(int id, String firstName, String lastName, String email, Boolean working, String password, int salary){
        super(id,firstName,lastName,email,working,password);
        if(id >= nextId) {
            nextId = id + 1;
        }
        this.salary = salary;
    }

    //To be able to use file handling with csv
    @Override
    public String getCSV(){
        return id + "," + firstName + "," + lastName + "," + email + "," + working + "," + password + "," + salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
