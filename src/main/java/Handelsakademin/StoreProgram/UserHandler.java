package Handelsakademin.StoreProgram;

import java.io.*;
import java.util.ArrayList;
/**
 * User handler stores information about all users in the store program
 * **/
public class UserHandler {
    //One list for all type of users
    private ArrayList<User> userList = new ArrayList<>();

    private File userFile = new File("users.txt");


    public UserHandler() {

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
        //Get users from file
        readUserFile();
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

    // Update employee using their id
    public void changeEmployee(Employee employee){
        for(User user: userList){
            if(user.isWorking() && employee.getId() == user.getId()){
                user = employee;
                return;
            }
        }
    }

    // Update customer using their id
    public void changeCustomer(Customer customer){
        for(User user: userList){
            if(!user.isWorking() && customer.getId() == user.getId()){
                user = customer;
                return;
            }
        }
    }

    //Create users from file if file exists otherwise create some default users
    public void readUserFile(){
        if(userFile.exists()) {
            try {
                FileReader fr = new FileReader(userFile);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                String[] tempArr;
                String delimiter = ",";
                int id;
                String firstName;
                String lastName;
                String email;
                boolean working;
                String password;
                while (line != null) {
                    tempArr = line.split(delimiter);
                    id = Integer.parseInt(tempArr[0]);
                    firstName = tempArr[1];
                    lastName = tempArr[2];
                    email = tempArr[3];
                    working = Boolean.parseBoolean(tempArr[4]);
                    password = tempArr[5];
                    if(working){
                        //Create employee and add to userList
                        int salary = Integer.parseInt(tempArr[6]);
                        Employee employee = new Employee(id, firstName, lastName, email, working, password, salary);
                        userList.add(employee);
                    }
                    else {
                        //Create customer and add to userList
                        String streetName = tempArr[6];
                        int zipCode = Integer.parseInt(tempArr[7]);
                        String city = tempArr[8];
                        Customer customer = new Customer(id, firstName, lastName, email, working, password, streetName, zipCode, city);
                        userList.add(customer);
                    }
                    //Read next line
                    line = br.readLine();
                }
                //Close file when done reading
                br.close();
            } catch (Exception e) {
                System.out.println("Error, something went wrong.");
            }
        }
        else if(!userFile.exists()){
            //Hard coded test users
            userList.add(new Employee("Ulf", "Bo", "ulfbo", 400, "123"));
            userList.add(new Customer("Bo", "Ek", "BoEk", "Gatan", 302, "Halmstad", "1234"));
        }
    }

    // Saves the userList in a file
    public void saveUserFile(){
        try{
            FileWriter fw = new FileWriter(userFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0; i < userList.size(); i++){
                //Write user to file
                bw.write(userList.get(i).getCSV(","));
                //So the file don't end with new line
                if(i < userList.size() - 1){
                    bw.newLine();
                }
            }
            bw.close();
        }
        catch (Exception e){
            System.out.println("Error, something went wrong.");
        }
    }
}
