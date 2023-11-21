package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginForm {
    private JPanel loginPanel;
    private JTextField emailField;
    private JTextField passwordField;
    private JButton createNewUserButton;
    private JButton loginButton;
    private JLabel messageLabel;
    private JFrame jFrame;

    // UserList to include all available user
    // For now there is a method at the bottom that has a few users when testing the application
    private ArrayList<User> userList = createUsers();

    public LoginForm() {
        jFrame = new JFrame();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(loginPanel);


        loginButton.addActionListener(new ActionListener() {

            // When the loginButton is pressed the userList is checked
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean foundUser = false;
                User tempUser = new User();
                for(User user: userList){
                    if(emailField.getText().equals(user.getEmail())){
                        if(passwordField.getText().equals(user.getPassword())){
                            foundUser = true;
                            tempUser = user;
                            break;
                        }
                    }
                }

                // If-statement to check if the user is customer, employee or doesn't exist as all
                if(foundUser && tempUser.isWorking()){
                    EmployeeForm employeeForm = new EmployeeForm();
                    jFrame.setVisible(false);
                }
                else if (foundUser && !tempUser.isWorking()) {
                    ShoppingForm shoppingForm = new ShoppingForm();
                    jFrame.setVisible(false);
                }
                else{
                   messageLabel.setText("Incorrect username or password");
                }

            }
        });
    }

    // Use this when wanting access to the loginForm in other forms
    public LoginForm getLoginForm(){
        return this;
    }

    //Can make the Login form visible or hide it
    public void setVisibility(boolean isVisible){
        jFrame.setVisible(isVisible);
    }

    // Adds some users to userList
    private ArrayList<User> createUsers(){
        ArrayList<User> users = new ArrayList<>();
        users.add(new Employee("Ulf", "Bo", "ulf.bo@mail.com", 400, "123"));
        users.add(new Customer("Bo", "Ek", "BoEk", "Gatan", 302, "Halmstad", "1234"));
        return users;
    }
}
