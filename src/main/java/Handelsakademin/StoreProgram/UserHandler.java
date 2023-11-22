package Handelsakademin.StoreProgram;

import java.util.ArrayList;
/**
 * User handler stores information about all users in the store program
 * **/
public class UserHandler {
    //One list for all type of users
    private ArrayList<User> userList = new ArrayList<>();

    public UserHandler() {
        //Hard coded test users
        userList.add(new Employee("Ulf", "Bo", "ulfbo", 400, "123"));
        userList.add(new Customer("Bo", "Ek", "BoEk", "Gatan", 302, "Halmstad", "1234"));
    }

    //Returns a list of customer users
    public ArrayList<User> getCustomers() {
        ArrayList<User> customerUsers = new ArrayList<>();
        for(User user: userList){
            if(!user.isWorking()){
                customerUsers.add(user);
            }
        }
        return customerUsers;
    }

    //Returns a list of employee users
    public ArrayList<User> getEmployees() {
        ArrayList<User> employeeUsers = new ArrayList<>();
        for(User user: userList){
            if(user.isWorking()){
                employeeUsers.add(user);
            }
        }
        return employeeUsers;
    }

    //Return list of all users
    public ArrayList<User> getAllUsers() {
        return userList;
    }

    //Adds a new customer to userList
    public void addNewCustomer(Customer customer) {
        userList.add(customer);
    }

    //Adds a new employee to userList
    public void addNewEmployee(Employee employee) {
        userList.add(employee);
    }
}
