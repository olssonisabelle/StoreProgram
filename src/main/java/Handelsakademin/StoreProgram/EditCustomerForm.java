package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCustomerForm {
    private JPanel editCustomerPanel;
    private JButton goBackButton;
    private JButton saveChangesButton;
    private JLabel customerNameLabel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField streetNameField;
    private JTextField zipCodeField;
    private JTextField cityField;
    private JLabel messageLabel;
    private JFrame jFrame;

    ShoppingForm shoppingForm;
    private Customer currentUser;

    public EditCustomerForm() {
        jFrame = new JFrame();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(editCustomerPanel);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoppingForm.setUserNameLable();
                shoppingForm.setVisibility(true);
                jFrame.dispose();

            }
        });


        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save jFrame-fields in variables
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();
                String streetName = streetNameField.getText();
                int zipCode = 0;
                try{
                    zipCode = Integer.parseInt(zipCodeField.getText());
                }
                catch(Exception ex){
                    messageLabel.setText("Zip code must be a number");
                }
                String city = cityField.getText();

                // Changes logged in employees information
                if(!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !password.isEmpty() && !streetName.isEmpty() && zipCode > 0 && !city.isEmpty()){
                    currentUser.setFirstName(firstName);
                    currentUser.setLastName(lastName);
                    currentUser.setEmail(email);
                    currentUser.setPassword(password);
                    currentUser.setStreetName(streetName);
                    currentUser.setZipCode(zipCode);
                    currentUser.setCity(city);
                    shoppingForm.getLoginForm().getUserHandler().changeCustomer((Customer) currentUser);
                    // Inform user that changes has been implemented
                    messageLabel.setText("Customer information has been changed");
                    customerNameLabel.setText(firstName + " " + lastName);
                    // Saves the changes in the file
                    shoppingForm.getLoginForm().getUserHandler().saveUserFile();

                }
            }
        });
    }

    public void setCustomerInformation(ShoppingForm shoppingForm){
        // Gets the reference value to shoppingForm
        this.shoppingForm = shoppingForm;
        // Changes fields to the information about the logged in customer
        currentUser = (Customer) shoppingForm.getLoginForm().getLogedInUser();
        customerNameLabel.setText(currentUser.getFirstName() + " " + currentUser.getLastName());
        firstNameField.setText(currentUser.getFirstName());
        lastNameField.setText(currentUser.getLastName());
        emailField.setText(currentUser.getEmail());
        passwordField.setText(currentUser.getPassword());
        streetNameField.setText(currentUser.getStreetName());
        zipCodeField.setText(Integer.toString(currentUser.getZipCode()));
        cityField.setText(currentUser.getCity());
    }
}


