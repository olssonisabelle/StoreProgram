package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeForm {

    private JPanel employeePanel;
    private JLabel employeeNameLabel;
    private JButton createEmployeeButton;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JButton createNewProductButton;
    private JButton logOutButton;
    private JFrame jFrame;

    private LoginForm loginform;

    public EmployeeForm(LoginForm loginForm) {
        jFrame = new JFrame();
        jFrame.setSize(700, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(employeePanel);
        this.loginform = loginForm;

        createEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateEmployeeForm createEmployeeForm = new CreateEmployeeForm();
                jFrame.setVisible(false);
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            // logOutButton to get back to loginForm
            @Override
            public void actionPerformed(ActionEvent e) {
                loginForm.setVisibility(true);
                jFrame.dispose();
            }
        });
    }

    public EmployeeForm getEmployeeForm(){
        return this;
    }
    public void setVisibility(boolean isVisible){
        jFrame.setVisible(isVisible);
    }
}
