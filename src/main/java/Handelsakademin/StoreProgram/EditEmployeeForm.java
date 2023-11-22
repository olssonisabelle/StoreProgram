package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JFrame jFrame;
    private EmployeeForm employeeForm;

    public EditEmployeeForm() {
        jFrame = new JFrame();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(editEmployeePanel);
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
