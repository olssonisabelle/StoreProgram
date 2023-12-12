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
    private JLabel totalPriceLabel;
    private JTextField quantityField;
    private JButton saveChangesButton;
    private JButton removeItemButton;
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
                    quantityField.setText("");
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
                    quantityField.setText(Integer.toString(order.getProductList().get(index).getQuantity()));
                    //Enable button if product is selected
                    removeItemButton.setEnabled(true);
                }
                else{
                    //Disables button if no product is selected
                    removeItemButton.setEnabled(false);
                }
            }
        });
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(productCartJList.getSelectedIndex()!= -1) {
                    try{
                        //Get index from productCartJList
                        int index = productCartJList.getSelectedIndex();
                        //Get id of the selected product
                        int getOrderProductId = order.getProductList().get(index).getId();
                        //Save new quantity that user inputted
                        int newQuantity = Integer.parseInt(quantityField.getText());
                        //Save the selected products index from the productList
                        int productListIndex = getProductListIndex(getOrderProductId);
                        //Calculate the available quantity of the selected product
                        int availableQuantity = productList.get(productListIndex).getQuantity() + order.getProductList().get(index).getQuantity();

                        //If statement to check that the inputted quantity is a valid input
                        if(newQuantity > 0 && newQuantity <= availableQuantity) {
                            //Update to new quantity in orderProductList
                            order.getProductList().get(index).setQuantity(newQuantity);
                            //Reset to storage quantity
                            productList.get(productListIndex).setQuantity(availableQuantity);
                            //Update quantity to what is remaining
                            productList.get(productListIndex).setQuantity(availableQuantity-newQuantity);
                            //Update user
                            messageLabel.setText("Quantity is now updated");
                        }
                        else {
                            //Update user
                            messageLabel.setText("Unavailable quantity");
                        }
                    }
                    catch (Exception ex){
                        //Update user
                        messageLabel.setText("Quantity has to be a number");
                    }
                }
                else {
                    //Update user
                    messageLabel.setText("No item in cart is selected");
                }
            }
        });

        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(productCartJList.getSelectedIndex()!= -1) {
                    int index = productCartJList.getSelectedIndex();
                    //Get id of the selected product
                    int getOrderProductId = order.getProductList().get(index).getId();
                    //Save the selected products index from the productList
                    int productListIndex = getProductListIndex(getOrderProductId);
                    //Calculate the available quantity of the selected product
                    int availableQuantity = productList.get(productListIndex).getQuantity() + order.getProductList().get(index).getQuantity();
                    //Reset to storage quantity
                    productList.get(productListIndex).setQuantity(availableQuantity);
                    //Removes the selected item from order
                    order.getProductList().remove(index);
                    // Update user
                    messageLabel.setText("Item is removed");
                    //Update JList of products in order
                    refreshJList();
                }
                else{
                    messageLabel.setText("No item in cart is selected");
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
        //Disables button for removing products
        removeItemButton.setEnabled(false);
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
    private int getProductListIndex(int getOrderProductId){
        for(int i = 0; i < productList.size(); i++){
            if(productList.get(i).getId() == getOrderProductId){
                return i;
            }
        }
        return -1;
    }

}
