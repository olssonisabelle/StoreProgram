package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateEmployeeForm {

    private JFrame jFrame;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField salaryField;
    private JTextField passwordField;
    private JButton createEmployeeButton;
    private JPanel createEmployeePanel;
    private JLabel messageLabel;
    private JButton goBackButton;
    private EmployeeForm employeeForm;

    public CreateEmployeeForm() {
        jFrame = new JFrame();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(createEmployeePanel);
        createEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Fetch all parameters from the JFrames
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                int salary = 0;
                try{
                    salary = Integer.parseInt(salaryField.getText());
                }
                catch(Exception ex){
                    messageLabel.setText("Please write a number for salary");
                }
                String password = passwordField.getText();
                //Check if all fields are filled in to create a new employee
                if(!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && salary > 0 && !password.isEmpty()) {
                    Employee newEmployee = new Employee(firstName, lastName, email, salary, password);
                    employeeForm.getLoginForm().getUserHandler().addNewEmployee(newEmployee);
                    //Update user message
                    messageLabel.setText("Successfully added new employee " + firstName + ".");
                    //Reset textFields
                    firstNameField.setText("");
                    lastNameField.setText("");
                    emailField.setText("");
                    salaryField.setText("");
                    passwordField.setText("");
                }
                else{
                    messageLabel.setText("Not enough information provided for creating a new employee");
                }

            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeForm.setVisibility(true);
                jFrame.dispose();
            }
        });
    }

    public void setEmployeeForm(EmployeeForm employeeForm){
        this.employeeForm = employeeForm;
    }
}
