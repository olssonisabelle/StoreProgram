package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InventoryForm {
    private JPanel inventoryPanel;
    private JLabel employeeNameLabel;
    private JList orderList;
    private JLabel orderLabel;
    private JList chosenOrderList;
    private JButton goBackButton;
    private JFrame jFrame;
    private EmployeeForm employeeForm;
    private DefaultListModel listModel = new DefaultListModel<>();

    public InventoryForm() {
        jFrame = new JFrame();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(inventoryPanel);
        jFrame.pack();
        orderList.setModel(listModel);

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
        refreshList();
    }

    private void refreshList() {
        //First, remove all elements from the list.
        listModel.removeAllElements();
        //Get orderList from orderHandler class
        OrderHandler orderHandler = new OrderHandler();
        orderHandler.readOrderList();
        ArrayList<Order> orders = orderHandler.getOrderList();
        //Add element to list
        for (Order order : orders) {
            listModel.addElement(order.getId());
        }

    }
}
