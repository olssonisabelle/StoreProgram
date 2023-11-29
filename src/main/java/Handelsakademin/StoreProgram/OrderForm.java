package Handelsakademin.StoreProgram;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderForm {
    private JPanel orderPanel;
    private JLabel customerNameLabel;
    private JList productCartJList;
    private JButton placeOrderButton;
    private JButton goBackButton;
    private JLabel messageLabel;
    private JLabel productNameLabel;
    private JLabel priceLabel;
    private JLabel quantityLabel;
    private JLabel totalPriceLabel;
    private JFrame jFrame;
    private ShoppingForm shoppingForm;
    private Customer logedinUser;
    private ArrayList<Product> products;
    private DefaultListModel defaultListModel = new DefaultListModel<>();
    private OrderHandler orderHandler;

    private Order order;

    public OrderForm() {
        jFrame = new JFrame();
        jFrame.pack();
        jFrame.setSize(700, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(orderPanel);
        productCartJList.setModel(defaultListModel);

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoppingForm.setVisibility(true);
                jFrame.dispose();
            }
        });
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderHandler = new OrderHandler();
                //  WHAT HAPPENED HERE?
                orderHandler.readOrderList();
                // Adds the new order to the orderList in orderHandler
                orderHandler.addNewOrder(order);
                // Updates the orderFile to include the new order
                orderHandler.saveOrderList();
                // Displays a message to the user
                messageLabel.setText("Order successfully added.");
                shoppingForm.resetOrder();
                // TODO FIX AVAILABLE QUANTITY MANAGEMENT
            }
        });

        productCartJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = productCartJList.getSelectedIndex();
                // Sets the labels to the selected products information
                productNameLabel.setText(order.getProductList().get(index).getName());
                priceLabel.setText(Integer.toString(order.getProductList().get(index).getPrice()));
                quantityLabel.setText(Integer.toString(order.getProductList().get(index).getQuantity()));
            }
        });
    }

    private void refreshJList(){
        defaultListModel.removeAllElements();
        // Adds all product to the JList
        for(Product product: order.getProductList()){
            defaultListModel.addElement(product.getName());
        }
    }
    public void setShoppingForm(ShoppingForm shoppingForm){
        this.shoppingForm = shoppingForm;
        //Get order
        order = shoppingForm.getOrder();
        logedinUser = (Customer) shoppingForm.getLoginForm().getLogedInUser();
        // Updates the JList to display the current order
        refreshJList();
        //Set total price
        totalPriceLabel.setText(calcTotalPrice() + " kr");
    }

    private int calcTotalPrice(){
        int totalPrice = 0;
        for(Product product: order.getProductList()){
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }

    public void setUserNameLabel(){
        customerNameLabel.setText(shoppingForm.getLoginForm().getLogedInUser().getFirstName() + " " + shoppingForm.getLoginForm().getLogedInUser().getLastName());
    }

    public OrderHandler getOrderHandler(){
        return orderHandler;
    }
}
