package Handelsakademin.StoreProgram;

public class Employee extends User{
    private int salary;
    private static int nextId = 0;

    public Employee(String firstName, String lastName, String email, boolean working, int salary, String password) {
        super(nextId, firstName, lastName, email, working, password);
        nextId ++;
        this.salary = salary;
    }
}
