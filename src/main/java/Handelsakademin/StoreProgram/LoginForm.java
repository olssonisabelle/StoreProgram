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

    private UserHandler userHandler = new UserHandler();

    private ProductHandler productHandler = new ProductHandler();

    private LoginForm loginForm = this;

    private User logedInUser = new User();

    public LoginForm() {
        jFrame = new JFrame();
        jFrame.pack();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(loginPanel);
        //A list of all users
        ArrayList<User> userList = userHandler.getAllUsers();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean foundUser = false;
                //Check userList for log in attempt
                for(User user: userList){
                    if(emailField.getText().equals(user.getEmail())){
                        if(passwordField.getText().equals(user.getPassword())){
                            foundUser = true;
                            logedInUser = user;
                            break;
                        }
                    }
                }

                //If-statement to check if the user is customer, employee or doesn't exist at all
                if(foundUser && logedInUser.isWorking()){
                    EmployeeForm employeeForm = new EmployeeForm();
                    //Add the product handler
                    employeeForm.setProductHandler(productHandler);
                    //To send loginForm information to another form
                    employeeForm.setLoginForm(loginForm);
                    //Display logged-in users name.
                    employeeForm.setUserNameLable();
                    hideAndEmptyFields();
                }
                else if (foundUser && !logedInUser.isWorking()) {
                    ShoppingForm shoppingForm = new ShoppingForm();
                    //To send loginForm information to another form
                    shoppingForm.setLoginForm(loginForm);
                    //Display logged-in users name.
                    shoppingForm.setUserNameLable();
                    hideAndEmptyFields();
                }
                else{
                   messageLabel.setText("Incorrect username or password");
                }

            }
        });
        createNewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateCustomerForm createCustomerForm = new CreateCustomerForm();
                createCustomerForm.setLoginForm(loginForm);
                messageLabel.setText("");
            }
        });
    }

    //Use this when wanting access to the loginForm in other forms
    public LoginForm getLoginForm(){
        return this;
    }

    //Can make the Login form visible or hide it
    public void setVisibility(boolean isVisible){
        jFrame.setVisible(isVisible);
    }

    public UserHandler getUserHandler() {
        return userHandler;
    }
    //To reset fields and hide form
    private void hideAndEmptyFields(){
        jFrame.setVisible(false);
        emailField.setText("");
        passwordField.setText("");
        messageLabel.setText("");
    }
    public User getLogedInUser(){
        return logedInUser;
    }
}
