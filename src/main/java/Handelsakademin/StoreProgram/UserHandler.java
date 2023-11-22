package Handelsakademin.StoreProgram;

import java.util.ArrayList;
/**
 * User handler stores information about all users in the store program
 * **/
public class UserHandler {



    private ArrayList<User> userList = new ArrayList<>();

    public UserHandler() {
        userList.add(new Employee("Ulf", "Bo", "ulf.bo@mail.com", 400, "123"));
        userList.add(new Customer("Bo", "Ek", "BoEk", "Gatan", 302, "Halmstad", "1234"));
    }

    public ArrayList<User> getCustomers() {
        ArrayList<User> customerUsers = new ArrayList<>();
        for(User user: userList){
            if(!user.isWorking()){
                customerUsers.add(user);
            }
        }
        return customerUsers;
    }

    public ArrayList<User> getEmployees() {
        ArrayList<User> employeeUsers = new ArrayList<>();
        for(User user: userList){
            if(user.isWorking()){
                employeeUsers.add(user);
            }
        }
        return employeeUsers;
    }

    public ArrayList<User> getAllUsers() {
        return userList;
    }

    public void addNewCustomer(Customer customer) {
        userList.add(customer);
    }

    public void addNewEmployee(Employee employee) {
        userList.add(employee);
    }



}
