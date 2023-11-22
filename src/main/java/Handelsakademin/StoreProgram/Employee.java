package Handelsakademin.StoreProgram;

public class Employee extends User{
    private int salary;
    private static int nextId = 0;

    public Employee(String firstName, String lastName, String email, int salary, String password) {
        super(nextId, firstName, lastName, email, true, password);
        nextId ++;
        this.salary = salary;
    }
    @Override
    public String getCSV(){
        return id + "," + firstName + "," + lastName + "," + email + "," + working + "," + password + "," + salary;
    }
}
