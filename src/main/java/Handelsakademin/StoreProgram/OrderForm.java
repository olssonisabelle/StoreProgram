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
    private ArrayList<Product> productList;
    private ArrayList<Product> checkProductsQuantityList;
    private ProductHandler productHandler;
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
                if(!order.getProductList().isEmpty()) {
                    orderHandler = new OrderHandler();
                    //  WHAT HAPPENED HERE?
                    orderHandler.readOrderList();
                    // Adds the new order to the orderList in orderHandler
                    orderHandler.addNewOrder(order);
                    // Updates the orderFile to include the new order
                    orderHandler.saveOrderList();
                    //Update available products
                    productList.clear();
                    productList.addAll(checkProductsQuantityList);
                    productHandler.saveProductFile();
                    // Displays a message to the user
                    messageLabel.setText("Order successfully added.");
                    // Reset order so that customer can place a new one
                    shoppingForm.resetOrder();
                    order = shoppingForm.getOrder();
                    // Empties JList
                    productCartJList.clearSelection();
                    defaultListModel.removeAllElements();
                    //Empties information about products and total price
                    productNameLabel.setText("");
                    priceLabel.setText("");
                    quantityLabel.setText("");
                    totalPriceLabel.setText("0");
                }
                else {
                    totalPriceLabel.setText("0");
                    messageLabel.setText("There are no products in your cart");
                }
            }
        });

        productCartJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(productCartJList.getSelectedIndex()!= -1) {
                    int index = productCartJList.getSelectedIndex();
                    // Sets the labels to the selected products information
                    productNameLabel.setText(order.getProductList().get(index).getName());
                    priceLabel.setText(Integer.toString(order.getProductList().get(index).getPrice()));
                    quantityLabel.setText(Integer.toString(order.getProductList().get(index).getQuantity()));
                }
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
        checkProductsQuantityList = shoppingForm.getCheckProductsQuantityList();
        productHandler = shoppingForm.getProductHandler();
        productList = productHandler.getProductList();
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

}
