package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditEmployeeForm {
    private JPanel editEmployeePanel;
    private JLabel employeeNameLabel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField salaryField;
    private JButton goBackButton;
    private JButton saveChangesButton;
    private JLabel messageLabel;
    private JFrame jFrame;
    private EmployeeForm employeeForm;

    private Employee currentUser;

    public EditEmployeeForm() {
        jFrame = new JFrame();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(editEmployeePanel);
        jFrame.pack();
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeForm.setUserNameLable();
                employeeForm.setVisibility(true);
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
                int salary = 0;
                try{
                    salary = Integer.parseInt(salaryField.getText());
                }
                catch(Exception ex){
                    messageLabel.setText("Salary must be a number");
                }
                // Changes logged in employees information
                if(!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !password.isEmpty() && salary > 0){
                    currentUser.setFirstName(firstName);
                    currentUser.setLastName(lastName);
                    currentUser.setEmail(email);
                    currentUser.setPassword(password);
                    currentUser.setSalary(salary);
                    employeeForm.getLoginForm().getUserHandler().changeEmployee((Employee) currentUser);
                    // Inform user that changes has been implemented
                    messageLabel.setText("Employee information has been changed");
                    employeeNameLabel.setText(firstName + " " + lastName);
                    // Saves the changes in the file
                    employeeForm.getLoginForm().getUserHandler().saveUserFile();

                }
            }
        });
    }

    // See about changing the name...
    public void setEmployeeInformation(EmployeeForm employeeForm){
        // Gets the reference value to employeeForm
        this.employeeForm = employeeForm;
        // Changes fields to the information about the logged in employee
        currentUser = (Employee) employeeForm.getLoginForm().getLogedInUser();
        employeeNameLabel.setText(currentUser.getFirstName() + " " + currentUser.getLastName());
        firstNameField.setText(currentUser.getFirstName());
        lastNameField.setText(currentUser.getLastName());
        emailField.setText(currentUser.getEmail());
        passwordField.setText(currentUser.getPassword());
        salaryField.setText(Integer.toString(currentUser.getSalary()));
    }

}
