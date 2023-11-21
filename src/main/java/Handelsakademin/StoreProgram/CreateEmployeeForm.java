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
    private JTextField streetField;
    private JTextField zipCodeField;
    private JTextField cityField;
    private JTextField passwordField;
    private JButton createEmployeeButton;
    private JPanel createEmployeePanel;

    public CreateEmployeeForm(UserHandler userHandler) {
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
                String password = passwordField.getText();
                Employee newEmployee = new Employee(firstName,
                                                    lastName,
                                                    email,
                                                    0,
                                                    password);
                userHandler.addNewEmployee(newEmployee);
            }
        });
    }

}
