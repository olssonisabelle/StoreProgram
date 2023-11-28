package Handelsakademin.StoreProgram;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private JLabel cusotmerNameLabel;
    private JFrame jFrame;
    private EmployeeForm employeeForm;
    private DefaultListModel listModel = new DefaultListModel<>();
    private DefaultListModel secondListModel = new DefaultListModel<>();
    private OrderHandler orderHandler = new OrderHandler();
    private ArrayList<Order> orders;

    public InventoryForm() {
        jFrame = new JFrame();
        jFrame.pack();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(inventoryPanel);
        orderList.setModel(listModel);
        chosenOrderList.setModel(secondListModel);

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeForm.setVisibility(true);
                jFrame.dispose();
            }
        });

        // When choosing an order in the JList in the center the information will show up on the left
        orderList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Get the selected order
                int index = orderList.getSelectedIndex();
                // Change the labels to display order id and which customer
                orderLabel.setText("Order: " + orders.get(index).getId());
                cusotmerNameLabel.setText(orders.get(index).getCustomerName());
                refreshChosenOrderList(index);
            }
        });
    }

    public void setEmployeeForm(EmployeeForm employeeForm){
        this.employeeForm = employeeForm;
        refreshOrderList();
        setUserNameLable();
    }

    private void refreshOrderList() {
        //First, remove all elements from the list.
        listModel.removeAllElements();
        //Get orderList from orderHandler class
        orderHandler.readOrderList();
        orders = orderHandler.getOrderList();
        //Add element to list
        for (Order order : orders) {
            listModel.addElement(order.getId());
        }

    }
    private void refreshChosenOrderList(int index){
        secondListModel.removeAllElements();
        // Placeholder for the products
        ArrayList <Product> productList = orders.get(index).getProductList();
        // Sets the JList to display the products name and how many the customer ordered
        for(Product product: productList){
            secondListModel.addElement(product.getName() + ", " + product.getQuantity());
        }

    }
    public void setUserNameLable(){
        employeeNameLabel.setText(employeeForm.getLoginForm().getLogedInUser().getFirstName() + " " + employeeForm.getLoginForm().getLogedInUser().getLastName());
    }
}
