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
    JFrame jFrame;

    public EmployeeForm() {
        jFrame = new JFrame();
        jFrame.setSize(700, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(employeePanel);
        createEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateEmployeeForm createEmployeeForm = new CreateEmployeeForm();
                jFrame.setVisible(false);
            }
        });
    }
}
