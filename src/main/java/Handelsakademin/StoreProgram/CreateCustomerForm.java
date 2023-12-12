package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCustomerForm {

    private JFrame jFrame;
    private JPanel createCustomerPanel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField streetField;
    private JTextField zipCodeField;
    private JTextField cityField;
    private JTextField passwordField;
    private JButton createCustomerButton;
    private JLabel messageLabel;
    private JButton goBackButton;
    private LoginForm loginForm;

    public CreateCustomerForm() {
        jFrame = new JFrame();
        jFrame.pack();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(createCustomerPanel);

        createCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Fetch all parameters from the JFrames
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String street = streetField.getText();
                int zipCode = 0;
                try {
                    zipCode = Integer.parseInt(zipCodeField.getText());
                } catch (Exception ex) {
                    messageLabel.setText("Please write a number for zip code.");
                }
                String city = cityField.getText();
                String password = passwordField.getText();
                if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !street.isEmpty() && !city.isEmpty() && !password.isEmpty() && !zipCodeField.getText().isEmpty()) {
                    if(zipCode <= 0 ){
                        messageLabel.setText("Please write a number for zip code.");
                    }
                    else {
                        Customer newCustomer = new Customer(firstName, lastName, email, street, zipCode, city, password);
                        //Add customer to userList
                        loginForm.getUserHandler().addNewCustomer(newCustomer);
                        //Save userList to file
                        loginForm.getUserHandler().saveUserFile();
                        //Update user message
                        messageLabel.setText("Successfully added new customer " + firstName + ".");
                        //Reset textFields
                        firstNameField.setText("");
                        lastNameField.setText("");
                        emailField.setText("");
                        streetField.setText("");
                        zipCodeField.setText("");
                        cityField.setText("");
                        passwordField.setText("");
                    }
                }
                else {
                    messageLabel.setText("Not enough information provided for creating a new customer.");
                }
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginForm.setVisibility(true);
                jFrame.dispose();
            }
        });
    }

    public void setLoginForm(LoginForm loginForm){
        this.loginForm = loginForm;
    }
}