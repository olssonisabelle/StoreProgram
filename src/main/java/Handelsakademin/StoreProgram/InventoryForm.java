package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryForm {
    private JPanel inventoryPanel;
    private JLabel employeeNameLabel;
    private JList orderList;
    private JLabel orderLabel;
    private JList chosenOrderList;
    private JButton goBackButton;
    private JFrame jFrame;
    private EmployeeForm employeeForm;

    public InventoryForm() {
        jFrame = new JFrame();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(inventoryPanel);

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
