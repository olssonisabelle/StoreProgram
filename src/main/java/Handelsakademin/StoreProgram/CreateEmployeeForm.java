package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateEmployeeForm {

    private JFrame jFrame;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;

    //TODO add salary field and remove unnecessary fields from Employee
    private JTextField salaryField;
    private JTextField passwordField;
    private JButton createEmployeeButton;
    private JPanel createEmployeePanel;
    private JLabel messageLabel;
    private JButton goBackButton;

    public CreateEmployeeForm() {
        LoginForm loginForm = new LoginForm();
        loginForm.getLoginForm();
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
                //TODO ?? add new checkers if the fields are empty and show it to the user, the same goes for all other fields
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
                if(!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && salary > 0 && !password.isEmpty()) {
                    Employee newEmployee = new Employee(firstName, lastName, email, salary, password);
                    loginForm.getUserHandler().addNewEmployee(newEmployee);
                    messageLabel.setText("Successfully added new employee.");
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
                //EmployeeForm employeeForm = new EmployeeForm();

            }
        });
    }

}
