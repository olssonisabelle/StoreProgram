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
    private JComboBox statusComboBox;
    private JButton changeStatusButton;
    private JLabel messageLabel;
    private DefaultComboBoxModel<String> model;
    private JLabel statusField;
    private JFrame jFrame;
    private EmployeeForm employeeForm;
    private DefaultListModel listModel = new DefaultListModel<>();
    private DefaultListModel secondListModel = new DefaultListModel<>();
    private OrderHandler orderHandler = new OrderHandler();
    private ArrayList<Order> orders;
    private int orderIndex = -1;

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
        // Creates an array of all possible order status
        String[] statusOptions = { "Ordered", "Received", "Packed", "Cancelled", "Failed", "Shipped", "Delivered"};
        model = new DefaultComboBoxModel<>(statusOptions);
        statusComboBox.setModel(model);


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
                messageLabel.setText("");
                // Get the selected order
                orderIndex = orderList.getSelectedIndex();
                // Change the labels to display order id and which customer
                orderLabel.setText("Order: " + orders.get(orderIndex).getId());
                cusotmerNameLabel.setText(orders.get(orderIndex).getCustomerName());
                refreshChosenOrderList(orderIndex);
            }
        });
        changeStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get the index of the comboBox
                int index = statusComboBox.getSelectedIndex();
                String newStatus = "";
                // Compares index to possible statuses and assigns which status is chosen
                switch (index) {
                    case 0:
                        newStatus = "ORDERED";
                        break;
                    case 1 :
                        newStatus = "RECEIVED";
                        break;
                    case 2:
                        newStatus = "PACKED";
                        break;
                    case 3:
                        newStatus = "CANCELLED";
                        break;
                    case 4:
                        newStatus = "FAILED";
                        break;
                    case 5:
                        newStatus = "SHIPPED";
                        break;
                    case 6:
                        newStatus = "DELIVERED";
                        break;
                }

                // Checks if any status and order has been chosen
                if(newStatus != null && orderIndex != -1) {
                    // Changes status on the oirder
                    orders.get(orderIndex).setStatus(newStatus);
                    // Updates user
                    messageLabel.setText("Status is changed");
                    // Updates orderFile
                    orderHandler.saveOrderList();
                }
                else{
                    messageLabel.setText("Failed to update status");
                }

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

        //Change the combobox to display the current orders status
        setCombobox(orders.get(index).getStatus());
    }

    public void setUserNameLable(){
        employeeNameLabel.setText(employeeForm.getLoginForm().getLogedInUser().getFirstName() + " " + employeeForm.getLoginForm().getLogedInUser().getLastName());
    }

    public void setCombobox(String status){
        // Updates the comboBox to match the chosen order's status
        switch (status) {
            case "ORDERED":
                statusComboBox.setSelectedIndex(0);
                break;
            case "RECEIVED":
                statusComboBox.setSelectedIndex(1);
                break;
            case "PACKED":
                statusComboBox.setSelectedIndex(2);
                break;
            case "CANCELLED":
                statusComboBox.setSelectedIndex(3);
                break;
            case "FAILED":
                statusComboBox.setSelectedIndex(4);
                break;
            case "SHIPPED":
                statusComboBox.setSelectedIndex(5);
                break;
            case "DELIVERED":
                statusComboBox.setSelectedIndex(6);
                break;
        }
    }
}
