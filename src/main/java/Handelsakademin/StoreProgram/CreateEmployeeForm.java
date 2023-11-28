package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JList employeeJList;
    private JLabel employeeNameLable;
    private DefaultListModel defaultListModel = new DefaultListModel<>();
    private EmployeeForm employeeForm;

    public CreateEmployeeForm() {
        jFrame = new JFrame();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(createEmployeePanel);
        jFrame.pack();
        employeeJList.setModel(defaultListModel);
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
                    //Add employee to userList
                    employeeForm.getLoginForm().getUserHandler().addNewEmployee(newEmployee);
                    //Save userList to file
                    employeeForm.getLoginForm().getUserHandler().saveUserFile();
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
                refreshList();
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
        setUserNameLable();
        refreshList();
    }

    private void refreshList(){
        //First, remove all elements from the list.
        defaultListModel.removeAllElements();
        //Get employeeList from userHandler class
        ArrayList<User> employeesList = employeeForm.getLoginForm().getUserHandler().getEmployees();
        //Add first and last name of the employees to the list
        for(User user: employeesList){
            defaultListModel.addElement(user.getFirstName() + " " + user.getLastName());
        }
    }

    //Fill in userNameLabel with the logged-in users full name
    public void setUserNameLable(){
        employeeNameLable.setText(employeeForm.getLoginForm().getLogedInUser().getFirstName() + " " + employeeForm.getLoginForm().getLogedInUser().getLastName());
    }

}
