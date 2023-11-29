package Handelsakademin.StoreProgram;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShoppingForm {
    private JPanel shoppingPanel;
    private JList productJList;
    private JTextField quantityField;
    private JButton addToCartButton;
    private JButton logOutButton;
    private JLabel userNameLable;
    private JButton editCustomerButton;
    private JButton checkOutButton;
    private JLabel priceLabel;
    private JLabel productNameLabel;
    private JLabel availableQuantityLabel;
    private JLabel messageLabel;
    private JFrame jFrame;
    private LoginForm loginForm;
    private ShoppingForm shoppingForm = this;
    private DefaultListModel defaultListModel = new DefaultListModel<>();
    private ProductHandler productHandler = new ProductHandler();
    private ArrayList <Product> productList;
    private Customer loggedInCustomer;
    private ArrayList<Product> checkProductsQuantityList;
    private Order order;


    public ShoppingForm() {
        jFrame = new JFrame();
        jFrame.pack();
        jFrame.setSize(700, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(shoppingPanel);
        productJList.setModel(defaultListModel);

        // Reads the productFile and updates productList
        productHandler.readProductFile();
        productList = productHandler.getProductList();
        // Adds all product to the JList
        for(Product product: productList){
            defaultListModel.addElement(product.getName());
        }

        productJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Clears the messageLabel
                messageLabel.setText("");
                // Gets the chosen index
                int index = productJList.getSelectedIndex();
                // Sets the labels to display the chosen product information
                productNameLabel.setText(productList.get(index).getName());
                priceLabel.setText(Integer.toString(productList.get(index).getPrice()));
                availableQuantityLabel.setText(Integer.toString(checkProductsQuantityList.get(index).getQuantity()));
                quantityField.setText("");
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            //logOutButton to get back to loginForm
            @Override
            public void actionPerformed(ActionEvent e) {
                loginForm.setVisibility(true);
                jFrame.dispose();
            }
        });
        editCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCustomerForm editCustomerForm = new EditCustomerForm();
                editCustomerForm.setCustomerInformation(shoppingForm);
                jFrame.setVisible(false);
                messageLabel.setText("");
            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!quantityField.getText().isEmpty()){
                    try{
                        int index = -1;
                        index = productJList.getSelectedIndex();
                        // Checks to see if any product is chosen
                        if(index > -1) {
                            int requiredQuantity = Integer.parseInt(quantityField.getText());
                            int availableQuantity = Integer.parseInt(availableQuantityLabel.getText());
                            Product product = new Product(productList.get(index).getId(), productList.get(index).getName(), productList.get(index).getPrice());
                            // Checks to see if there is enough products to place that order
                            if (requiredQuantity <= availableQuantity && requiredQuantity > 0) {
                                product.setQuantity(requiredQuantity);
                                order.addProductToOrder(product);
                                //Update available quantity for product
                                checkProductsQuantityList.get(index).setQuantity(availableQuantity-requiredQuantity);
                                availableQuantityLabel.setText(Integer.toString(checkProductsQuantityList.get(index).getQuantity()));
                                //Update user
                                messageLabel.setText("Product successfully added to cart");
                                //Reset quantity field
                                quantityField.setText("");

                            }
                            else {
                                messageLabel.setText("Quantity not acceptable");
                            }
                        }
                        else{
                            messageLabel.setText("Please choose a product");
                        }
                    }
                    catch (Exception ex){
                        messageLabel.setText("The quantity has to be a number");
                    }
                }
                else{
                    messageLabel.setText("Please write a quantity");
                }
            }
        });
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderForm orderForm = new OrderForm();
                orderForm.setShoppingForm(shoppingForm);
                orderForm.setUserNameLabel();
                jFrame.setVisible(false);
                messageLabel.setText("");
            }
        });


    }

    public Order getOrder(){
        return order;
    }

    public void resetOrder(){
        // To get the right id for order
        OrderHandler orderHandler = new OrderHandler();
        orderHandler.readOrderList();
        // Create order for loggedInCustomer
        order = new Order(loggedInCustomer);
    }

    public void setLoginForm(LoginForm loginForm){
        this.loginForm = loginForm;
        loggedInCustomer = (Customer) loginForm.getLogedInUser();
        resetOrder();
        copyProductList();
    }

    public void copyProductList(){
        checkProductsQuantityList = new ArrayList<>();
        checkProductsQuantityList.addAll(productList);
    }

    public void setVisibility(boolean isVisible){
        jFrame.setVisible(isVisible);
    }
    public LoginForm getLoginForm(){
        return loginForm;
    }

    //Fill in userNameLabel with the logged-in users full name
    public void setUserNameLable(){
        userNameLable.setText(loginForm.getLogedInUser().getFirstName() + " " + loginForm.getLogedInUser().getLastName());
    }
    public ArrayList<Product> getCheckProductsQuantityList(){
        return checkProductsQuantityList;
    }
    public ProductHandler getProductHandler(){
        return productHandler;
    }
}
