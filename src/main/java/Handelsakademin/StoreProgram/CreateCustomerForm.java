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

    public CreateCustomerForm(UserHandler userHandler) {
        jFrame = new JFrame();
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
                if(firstName.isEmpty()) {
                    //TODO ?? add new checkers if the fields are empty and show it to the user, the same goes for all other fields
                }
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String street = streetField.getText();
                int zipCode = 0;
                try {
                    zipCode = Integer.parseInt(zipCodeField.getText());
                } catch (Exception ex) {

                }
                String city = cityField.getText();
                String password = passwordField.getText();
                Customer newCustomer = new Customer(firstName,
                        lastName,
                        email,
                        street,
                        zipCode,
                        city,
                        password);
                userHandler.addNewCustomer(newCustomer);
            }
        });
    }
}