package Handelsakademin.StoreProgram;

import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        UserHandler userHandler = new UserHandler();
        createTestCustomerUsers(userHandler);
        createTestEmployeeUsers(userHandler);
        LoginForm loginForm = new LoginForm(userHandler);

    }

    // Adds some test users to userList
    public static void createTestEmployeeUsers(UserHandler userHandler){
        userHandler.addNewEmployee(new Employee("Ulf", "Bo", "ulf.bo@mail.com", 400, "123"));
    }

    // Adds some test users to userList
    private static void createTestCustomerUsers(UserHandler userHandler){
        userHandler.addNewCustomer(new Customer("Bo", "Ek", "BoEk", "Gatan", 302, "Halmstad", "1234"));
    }

}
