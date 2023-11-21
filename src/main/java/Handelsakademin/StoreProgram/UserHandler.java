package Handelsakademin.StoreProgram;

import java.util.ArrayList;
/**
 * User handler stores information about all users in the store program
 * **/
public class UserHandler {
    ArrayList<User> customerUsers = new ArrayList<>();
    ArrayList<User> employeeUsers = new ArrayList<>();

    public UserHandler() {
    }

    public ArrayList<User> getCustomers() {
        return customerUsers;
    }

    public ArrayList<User> getEmployees() {
        return employeeUsers;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        allUsers.addAll(customerUsers);
        allUsers.addAll(employeeUsers);
        return allUsers;
    }

    public void addNewCustomer(Customer customer) {
        customerUsers.add(customer);
    }

    public void addNewEmployee(Employee employee) {
        employeeUsers.add(employee);
    }
}
