package Handelsakademin.StoreProgram;

import javax.swing.*;
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
    private JTextField quantityField;
    private JLabel priceLabel;
    private JFrame jFrame;
    private ShoppingForm shoppingForm;
    private Customer logedinUser;
    private ArrayList<Product> products;
    private OrderHandler orderHandler;

    public OrderForm() {
        jFrame = new JFrame();
        jFrame.setSize(700, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(orderPanel);
        jFrame.pack();

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
                orderHandler.addNewOrder(products,logedinUser);
                orderHandler.saveOrderLst();
                messageLabel.setText("Order successfully added.");
            }
        });
    }

    public void setShoppingForm(ShoppingForm shoppingForm){
        this.shoppingForm = shoppingForm;
        //To test orderForm we use hard coded values that are "send" from shoppingForm to oderForm
        products = new ArrayList<>();
        products.add(new Product(1, "T-shirt", 100, 1));
        products.add(new Product(2, "Pants", 200, 1));
        products.add(new Product(3, "Shoes", 300, 2));
        products.add(new Product(4, "Skirt", 150, 1));
        logedinUser = (Customer) shoppingForm.getLoginForm().getLogedInUser();
    }

    public OrderHandler getOrderHandler(){
        return orderHandler;
    }
}
