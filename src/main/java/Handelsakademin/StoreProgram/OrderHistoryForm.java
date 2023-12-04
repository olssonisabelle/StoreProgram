package Handelsakademin.StoreProgram;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderHistoryForm {
    public JFrame jFrame;
    private JPanel orderHistoryPanel;
    private JLabel userNameLabel;
    private JList orderJList;
    private JButton goBackButton;
    private JLabel orderIdLabel;
    private JList productJList;
    private JLabel totalPriceLabel;
    private JLabel statusLabel;
    private ShoppingForm shoppingForm;
    ArrayList <Order> orderList;
    private DefaultListModel orderDefaultListModel = new DefaultListModel<>();
    private DefaultListModel productDefaultListModel = new DefaultListModel<>();
    OrderHandler orderHandler;


    public OrderHistoryForm() {
        jFrame = new JFrame();
        jFrame.pack();
        jFrame.setSize(700, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(orderHistoryPanel);
        orderJList.setModel(orderDefaultListModel);
        productJList.setModel(productDefaultListModel);


        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoppingForm.setVisibility(true);
                jFrame.dispose();
            }
        });


        orderJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = orderJList.getSelectedIndex();
                //Sets the orderIdLabel to the selected order's id
                orderIdLabel.setText(orderList.get(index).getId() + "");
                //Updates the productJList to the selected order's information
                refreshProductJList(index);
            }
        });
    }

    public void setShoppingForm(ShoppingForm shoppingForm){
        this.shoppingForm = shoppingForm;
        totalPriceLabel.setText("");
        //Gets the logged-in users orders
        getCustomerOrders();
        //Updates the OrderJList
        refreshOrderJList();
        //Sets the UserNameLabel to the logged-in user's name
        setUserNameLabel();

    }

    public void setUserNameLabel(){
        userNameLabel.setText(shoppingForm.getLoginForm().getLogedInUser().getFirstName() + " " + shoppingForm.getLoginForm().getLogedInUser().getLastName());
    }

    public void getCustomerOrders(){
        // Creates a new orderHandler and reads the orderFile
        orderHandler = new OrderHandler();
        orderHandler.readOrderList();

        // Creates an orderList containing only the logged-in users orders
        orderList = orderHandler.getOrderListByCustomer(shoppingForm.getLoginForm().getLogedInUser().getId());

    }
    private void refreshOrderJList(){
        orderDefaultListModel.removeAllElements();
        // Adds all product to the JList
        for(Order order: orderList){
            orderDefaultListModel.addElement(order.getId());
        }
    }

    private void refreshProductJList(int index){
        productDefaultListModel.removeAllElements();
        // Placeholder for the products
        ArrayList <Product> productList = orderList.get(index).getProductList();
        // Sets the JList to display the products name and how many the customer ordered
        for(Product product: productList){
            productDefaultListModel.addElement(product.getName() + ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity());
        }

        // Calculate the total price of the selected order
        int totalPrice = 0;
        for (Product product: productList){
            totalPrice += product.getPrice() * product.getQuantity();
        }
        //Changes the totalPriceLabel
        totalPriceLabel.setText(Integer.toString(totalPrice));

        // Displays the current status of the selected order
        statusLabel.setText(orderList.get(index).getStatus().toLowerCase());
    }


}
