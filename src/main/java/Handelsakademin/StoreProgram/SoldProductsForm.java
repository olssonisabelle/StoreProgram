package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SoldProductsForm {
    private JFrame jFrame;
    private EmployeeForm employeeForm;
    private JLabel soldProductsNameLabel;
    private JList soldProductsList;
    private JPanel SoldProductsList;
    private JPanel soldProductsPanel;
    private JButton goBackButton;
    private DefaultListModel listModel = new DefaultListModel<>();
    private OrderHandler orderHandler = new OrderHandler();
    private ArrayList<Order> orders;

    public SoldProductsForm() {
        jFrame = new JFrame();
        jFrame.pack();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(soldProductsPanel);
        soldProductsList.setModel(listModel);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeForm.setVisibility(true);
                jFrame.dispose();
            }
        });
    }

    public void setEmployeeNameLabel() {
       soldProductsNameLabel.setText(employeeForm.getLoginForm().getLogedInUser().getFirstName() + " " + employeeForm.getLoginForm().getLogedInUser().getLastName());
    }
    public void setEmployeeForm(EmployeeForm employeeForm) {
        this.employeeForm = employeeForm;
        setEmployeeNameLabel();
        setSoldProducts();
    }

    private void setSoldProducts() {
        orderHandler.readOrderList();
        orders = orderHandler.getOrderList();
        HashMap<String, Integer> productNameToQuantity = new HashMap<>();
        for(Order order : orders) {
            ArrayList<Product> productList = order.getProductList();
            for(Product product : productList) {
                String name = product.getName();
                int quantity = product.getQuantity();
                boolean alreadyAdded = productNameToQuantity.containsKey(name);
                if(alreadyAdded){
                    Integer oldQuantity = productNameToQuantity.get(name);
                    int newQuantity = oldQuantity + quantity;
                    productNameToQuantity.put(name, newQuantity);
                } else {
                    productNameToQuantity.put(name, quantity);
                }
            }
        }
        Set<Map.Entry<String, Integer>> entries = productNameToQuantity.entrySet();
        for(Map.Entry entry : entries) {
            String name = (String) entry.getKey();
            Integer quantity = (Integer) entry.getValue();
            listModel.addElement(name + ", " +quantity);
        }
    }
}
